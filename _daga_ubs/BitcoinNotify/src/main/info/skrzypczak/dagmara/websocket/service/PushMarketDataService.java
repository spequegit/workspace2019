package main.info.skrzypczak.dagmara.websocket.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;

import org.knowm.xchange.currency.CurrencyPair;

import com.google.common.collect.Sets;

import info.bitrich.xchangestream.bitstamp.BitstampStreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import main.info.skrzypczak.dagmara.dao.AlertStore;
import main.info.skrzypczak.dagmara.dao.ThirdPartiesActors;
import main.info.skrzypczak.dagmara.entity.Message;
import main.info.skrzypczak.dagmara.entity.Message.MessageBuilder;

public class PushMarketDataService implements Runnable {

	private static PushMarketDataService instance;
	private static final Set<AlertService> wsAlerts = new HashSet<>();

	private PushMarketDataService() {
	}

	public static void initialize() {
		if (instance == null) {
			instance = new PushMarketDataService();
			new Thread(instance).start();
		}
	}

	public static void subscribe(AlertService a) {
		wsAlerts.add(a);
	}

	public static void unSubscribe(AlertService a) {
		wsAlerts.remove(a);
	}

	public static Set<AlertService> getSubscribshins() {
		return Sets.newHashSet(wsAlerts);
	}

	public static void clearSubscribshins() {
		wsAlerts.clear();
	}

	public static void broadcast(String text) throws IOException, EncodeException {

		wsAlerts.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					Message message = new MessageBuilder().from(ThirdPartiesActors.SERVER.valueOf()).to(endpoint.user)
							.sessionId(endpoint.session.getId()).message(text).build();
					endpoint.session.getBasicRemote().sendObject(message);
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void run() {

		StreamingExchange exchange = StreamingExchangeFactory.INSTANCE
				.createExchange(BitstampStreamingExchange.class.getName());
		exchange.connect().blockingAwait();

		exchange.getStreamingMarketDataService().getTrades(CurrencyPair.BTC_USD).subscribe(trade -> {
			String pairString = trade.getCurrencyPair().toString();
			String pattern = "{ \"pair\":\"%s\", \"price\":\"%s\"}";
			if (aboveTreshold(pairString, trade.getPrice())) {
				broadcast(String.format(pattern, pairString, trade.getPrice()));
			}
		}, throwable -> {
			System.out.println("Error in subscribing trades. " + throwable);
		});
	}

	private boolean aboveTreshold(String pair, BigDecimal price) {
		AlertStore store = AlertStore.getInstance();
		if (store.contains(pair)) {
			BigDecimal limit = store.get(pair).getLimit();
			return (limit.compareTo(price) == 0 || price.compareTo(limit) == 1);
		}
		return false;
	}

}