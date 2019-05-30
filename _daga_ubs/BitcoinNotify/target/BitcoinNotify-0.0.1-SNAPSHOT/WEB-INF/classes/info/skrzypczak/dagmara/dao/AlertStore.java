package main.info.skrzypczak.dagmara.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import com.google.common.collect.Maps;

import main.info.skrzypczak.dagmara.entity.Alert;

public class AlertStore {

	private final HashMap<String, BigDecimal> ALERTS = new HashMap<String, BigDecimal>();

	private static AlertStore instance;

	private AlertStore() {
	}

	public static AlertStore getInstance() {
		if (instance == null)
			instance = new AlertStore();
		return instance;
	}

	public BigDecimal add(Alert alert) {
		return ALERTS.put(alert.getPair(), alert.getLimit());
	}

	public Alert get(String pair) {
		return ALERTS.containsKey(pair) ? new Alert(pair, ALERTS.get(pair)) : null;
	}

	public Boolean contains(String pair) {
		return ALERTS.containsKey(pair);
	}

	public BigDecimal remove(String pair) {
		return ALERTS.remove(pair);
	}

	public int size() {
		return ALERTS.size();
	}

	public void clear() {
		ALERTS.clear();
	}

	public HashMap<String, BigDecimal> getAlerts() {
		HashMap<String, BigDecimal> map = Maps.newHashMap();
		map.putAll(ALERTS);
		return map;
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("AlertStore [");
		ALERTS.forEach((k, v) -> b.append("\"pair\":\"" + k + "\"" + " \"limit\":" + "\"" + v.toString() + "\""));
		b.append("]");
		return b.toString();
	}

}
