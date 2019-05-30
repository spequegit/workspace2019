package test.info.skrzypczak.dagmara.rest.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;

import main.info.skrzypczak.dagmara.dao.AlertStore;
import main.info.skrzypczak.dagmara.entity.Alert;
import main.info.skrzypczak.dagmara.rest.service.LimitationService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LimitationServiceTest {

	private static final String PAIR = "PAIR";

	@Mock
	AlertStore alertStore;

	@Mock
	Alert alert;

	Gson gson;

	LimitationService limitationService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		limitationService = new LimitationService();
		gson = new Gson(); // final class
	}

	@AfterEach
	public void closeUp() {

	}

	@Test
	final void shoudFindAlertInCaseOfEmptyStore() {
		// GIVEN
//		Mockito.when(this.alertStore.contains(PAIR)).thenReturn(Boolean.FALSE);

		// WHEN
		String result = limitationService.findAlert(PAIR);

		// THEN
		assertEquals("{}", result);
	}

	@Test
	final void shoudAddAllert() {
		// GIVEN
//		Mockito.when(this.alertStore.contains(PAIR)).thenReturn(Boolean.FALSE);

		// WHEN
		Response result = limitationService.addAlert(null);

		// THEN
		assertEquals(123, result.getStatus());
	}

}
