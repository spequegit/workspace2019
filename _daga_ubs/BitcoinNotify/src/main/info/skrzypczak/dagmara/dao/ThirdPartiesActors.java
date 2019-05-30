package main.info.skrzypczak.dagmara.dao;

public enum ThirdPartiesActors {
	BLACK_BOX("Black Box"), ALL("All Subscribers"), NO_ONE("No One"), SERVER("ws://localhost:8080/BitcoinNotify");

	private final String displayName;

	private ThirdPartiesActors(String displayName) {
		this.displayName = displayName;
	}

	public String valueOf() {
		return this.displayName;
	}

}
