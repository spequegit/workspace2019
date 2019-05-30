package main.info.skrzypczak.dagmara.websocket.service;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import main.info.skrzypczak.dagmara.dao.ThirdPartiesActors;
import main.info.skrzypczak.dagmara.entity.Message;
import main.info.skrzypczak.dagmara.entity.Message.MessageBuilder;
import main.info.skrzypczak.dagmara.entity.MessageDecoder;
import main.info.skrzypczak.dagmara.entity.MessageEncoder;

@ServerEndpoint(value = "/alerts/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class AlertService {

	Session session;
	String user;
	MessageBuilder mBuilder;

	public AlertService() {
		mBuilder = new MessageBuilder();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String userName) throws IOException, EncodeException {
		this.session = session;
		this.user = userName;
		Message message = mBuilder.from(ThirdPartiesActors.SERVER.valueOf()).to(userName).sessionId(session.getId())
				.message("connected").build();
		sendPrivateMessage(session, message);
		PushMarketDataService.initialize();
		PushMarketDataService.subscribe(this);
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {
		Message message = mBuilder.from(ThirdPartiesActors.SERVER.valueOf()).to(user).sessionId(session.getId())
				.message("connection closed").build();
		sendPrivateMessage(session, message);
		PushMarketDataService.initialize();
		PushMarketDataService.unSubscribe(this);

	}

	@OnMessage
	public void onMessage(String mes, Session session) throws IOException, EncodeException {
		Message message = mBuilder.from(ThirdPartiesActors.SERVER.valueOf()).to(user).sessionId(session.getId())
				.message("Message received: " + mes).build();
		sendPrivateMessage(session, message);
	}

	@OnError
	public void onError(Throwable t) throws IOException, EncodeException {
		mBuilder = mBuilder.from(ThirdPartiesActors.SERVER.valueOf()).to(ThirdPartiesActors.BLACK_BOX.valueOf())
				.message("onError: " + t.getMessage());
		if (session != null) {
			mBuilder = mBuilder.sessionId(session.getId());
		}
		sendPrivateMessage(session, mBuilder.build());
	}

	private static void sendPrivateMessage(Session session, Message message) throws IOException, EncodeException {
		session.getBasicRemote().sendObject(message);
	}

	public void setMessageBuilder(MessageBuilder mBuilder) {
		this.mBuilder = mBuilder;

	}

}
