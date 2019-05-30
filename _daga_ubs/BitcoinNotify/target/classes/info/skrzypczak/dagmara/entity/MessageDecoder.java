package main.info.skrzypczak.dagmara.entity;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageDecoder implements Decoder.Text<Message> {

	private static Gson gson = new Gson();

	@Override
	public Message decode(String m) throws DecodeException {
		return gson.fromJson(m, Message.class);
	}

	@Override
	public boolean willDecode(String m) {
		return (m != null);
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
	}

	@Override
	public void destroy() {
	}
}