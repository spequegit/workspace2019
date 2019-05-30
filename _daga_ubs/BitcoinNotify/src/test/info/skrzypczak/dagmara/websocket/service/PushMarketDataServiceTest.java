package test.info.skrzypczak.dagmara.websocket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.websocket.EncodeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import main.info.skrzypczak.dagmara.websocket.service.AlertService;
import main.info.skrzypczak.dagmara.websocket.service.PushMarketDataService;

@RunWith(JUnitPlatform.class)
public class PushMarketDataServiceTest {

	public PushMarketDataService pmds;

	@Mock
	AlertService alertService1;

	@Mock
	AlertService alertService2;

	@Mock
	AlertService alertService3;

	@BeforeEach
	public void setUp() {
		PushMarketDataService.initialize();
		PushMarketDataService.clearSubscribshins();
	}

	@Test
	final void shouldSubscribe() {
		// Given
		// When
		PushMarketDataService.subscribe(alertService1);

		// Then
		assertEquals(1, PushMarketDataService.getSubscribshins().size());
	}

	@Test
	final void shouldUnSubscribe() {
		// Given
		// When
		PushMarketDataService.subscribe(alertService1);
		PushMarketDataService.unSubscribe(alertService1);

		// Then
		assertEquals(0, PushMarketDataService.getSubscribshins().size());
	}

	@Test
	final void shouldBroadcast() throws IOException, EncodeException {
		// Given
		PushMarketDataService.subscribe(alertService1);
		// When
		PushMarketDataService.broadcast("text");
		// Then
		assertEquals(0, PushMarketDataService.getSubscribshins().size());
	}

}
