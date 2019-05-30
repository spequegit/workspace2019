package main.info.skrzypczak.dagmara.entity;

public class Message {

	protected final String from;
	protected final String sessionId;
	protected final String to;
	protected final String message;

	public Message(String from, String sessionId, String to, String message) {
		this.from = from;
		this.sessionId = sessionId;
		this.to = to;
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getMessage() {
		return message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public static class MessageBuilder {

		protected String from;
		protected String sessionId;
		protected String to;
		protected String message;

		public static MessageBuilder fromMessage(Message message) {
			return new MessageBuilder().from(message.getFrom()).sessionId(message.getSessionId()).to(message.getTo())
					.message(message.getMessage());
		}

		public MessageBuilder from(String from) {
			this.from = from;
			return this;
		}

		public MessageBuilder sessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}

		public MessageBuilder to(String to) {
			this.to = to;
			return this;
		}

		public MessageBuilder message(String message) {
			this.message = message;
			return this;
		}

		public Message build() {
			return new Message(from, sessionId, to, message);
		}
	}
}
