package test.info.skrzypczak.dagmara.websocket.service;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import main.info.skrzypczak.dagmara.entity.Message;
import main.info.skrzypczak.dagmara.entity.Message.MessageBuilder;
import main.info.skrzypczak.dagmara.websocket.service.AlertService;

@RunWith(JUnitPlatform.class)
public class AlertServiceTest {

	@Mock
	Session session;

	@Mock
	MessageBuilder mBuilder;

	@Mock
	Message m;

	AlertService as;

	@BeforeEach
	public void setUp() {
		as = new AlertService();
		as.setMessageBuilder(mBuilder);
	}

	@Test
	final void shouldDoAlertService() throws IOException, EncodeException {
		// Given
		as.onClose(session);

		// When
		Mockito.doReturn(m).when(mBuilder).build();
//		Mockito.doReturn(mBuilder).when(mBuilder).to(anyString);
		// Then
	}

}
