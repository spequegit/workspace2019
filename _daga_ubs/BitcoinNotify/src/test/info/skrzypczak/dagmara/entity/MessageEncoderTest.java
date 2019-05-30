package test.info.skrzypczak.dagmara.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.websocket.EncodeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.info.skrzypczak.dagmara.entity.Message;
import main.info.skrzypczak.dagmara.entity.MessageEncoder;

@RunWith(JUnitPlatform.class)
public class MessageEncoderTest {

	MessageEncoder messageEncoder;

	Message m;
	private static final String FROM = "from";
	private static final String SESSIONID = "sessionId";
	private static final String TO = "to";
	private static final String MESSAGE = "message";
	private static final String GSON = "{\"from\":\"from\",\"sessionId\":\"sessionId\",\"to\":\"to\",\"message\":\"message\"}";

	@BeforeEach
	public void setUp() throws Exception {
		messageEncoder = new MessageEncoder();
		m = new Message(FROM, SESSIONID, TO, MESSAGE);
	}

	@Test
	final void shouldEncode() throws EncodeException {
		String result = messageEncoder.encode(m);
		assertEquals(GSON, result);
	}

}
