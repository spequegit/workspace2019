package test.info.skrzypczak.dagmara.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.websocket.DecodeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.info.skrzypczak.dagmara.entity.Message;
import main.info.skrzypczak.dagmara.entity.MessageDecoder;

@RunWith(JUnitPlatform.class)
public class MessageDecoderTest {

	private static final String FROM = "from";
	private static final String SESSIONID = "sessionId";
	private static final String TO = "to";
	private static final String MESSAGE = "message";
	private static final String GSON = "{\"from\":\"from\",\"sessionId\":\"sessionId\",\"to\":\"to\",\"message\":\"message\"}";

	MessageDecoder messageDecoder;

	@BeforeEach
	public void setUp() {
		messageDecoder = new MessageDecoder();
	}

	@Test
	final void WillDecodeShouldBeTrue() {
		// Given
		// When
		Boolean result = messageDecoder.willDecode(MESSAGE);
		// Then
		assertTrue(result);

	}

	@Test
	final void WillDecodeShouldBeFalse() {
		// Given
		// When
		Boolean result = messageDecoder.willDecode(null);
		// Then
		assertFalse(result);
	}

	@Test
	final void ShouldDecode() throws DecodeException {
		// Given
		// When
		Message result = messageDecoder.decode(GSON);
		// Then
		assertEquals(FROM, result.getFrom());
		assertEquals(SESSIONID, result.getSessionId());
		assertEquals(TO, result.getTo());
		assertEquals(MESSAGE, result.getMessage());
	}

}
