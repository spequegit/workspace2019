package test.info.skrzypczak.dagmara.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.info.skrzypczak.dagmara.dao.AlertStore;
import main.info.skrzypczak.dagmara.entity.Alert;

@RunWith(JUnitPlatform.class)
public class AlertStoreTest {

	public AlertStore alertStore;
	public Alert alert1;
	public Alert alert2;
	public Alert alert3;

	@BeforeEach
	public void setUp() {
		alertStore = AlertStore.getInstance();
		alert1 = new Alert("ALA", new BigDecimal(234.5));
		alert2 = new Alert("OLA", new BigDecimal(345.5));
		alert3 = new Alert("ELA", new BigDecimal(567.5));
		alertStore.clear();

	}

	@Test
	public void uniquenesInCaseOfSameAlertsWithSameObjectReference() {
		// Given
		// When
		alertStore.add(alert1);
		alertStore.add(alert1);
		alertStore.add(alert1);

		// Then
		assertEquals(1, alertStore.size());
	}

	@Test
	public void uniquenesInCaseOfSameAlertsWithDifferentObjectReference() {
		// Given
		alert1.setPair("CUSTOM");
		alert2.setPair("CUSTOM");
		alert3.setPair("CUSTOM");

		// When
		alertStore.add(alert1);
		alertStore.add(alert2);
		alertStore.add(alert3);

		// Then
		assertEquals(1, alertStore.size());
	}

	@Test
	public void latestLimitValueInCaseOfSameAlertsWithDifferentObjectReference() {
		// Given
		BigDecimal first = new BigDecimal(123.11);
		BigDecimal last = new BigDecimal(456.4);
		alert1.setPair("CUSTOM");
		alert1.setLimit(first);
		alert2.setPair("CUSTOM");
		alert3.setPair("CUSTOM");
		alert3.setLimit(last);

		// When
		alertStore.add(alert1);
		alertStore.add(alert2);
		alertStore.add(alert3);

		// Then
		assertEquals(last, alertStore.get("CUSTOM").getLimit());
	}

	@Test
	public void uniquenesInCaseOfModifyedAlerts() {
		// Given
		Alert alert = new Alert();
		alert.setPair("ALA");
		alert.setLimit(new BigDecimal(234.5));

		// When
		alertStore.add(alert);
		alert.setLimit(new BigDecimal(234.6));
		alertStore.add(alert);
		alert.setPair("OLA");
		alertStore.add(alert);

		// Then
//		assertEquals(3, alertStore.size());
	}

	@Test
	public void uniquenesInCaseOfMultipleAlerts() {
		// Given
		Alert alert1 = new Alert();
		alert1.setPair("ALA");
		alert1.setLimit(new BigDecimal(234.5));

		Alert alert2 = new Alert();
		alert2.setPair("OLA");
		alert2.setLimit(new BigDecimal(234.5));

		Alert alert3 = new Alert();
		alert3.setPair("ALA");
		alert3.setLimit(new BigDecimal(234.6));

		// When
		alertStore.add(alert1);
		alertStore.add(alert2);
		alertStore.add(alert3);

		// Then
//		assertEquals(3, alertStore.size());
	}

	@Test
	public void uniquenesInCaseOfNullAlerts() {
		// Given
		Alert alert1 = new Alert();
		Alert alert2 = new Alert();

		// When
		alertStore.add(alert1);
		alertStore.add(alert2);

		// Then
//		assertEquals(1, alertStore.size());
	}

	@Test
	public void toStringCustomImplementation() {
		// Given
		Alert alert1 = new Alert();
		alert1.setPair("ALA");
		alert1.setLimit(new BigDecimal(123.4));

		Alert alert2 = new Alert();
		alert2.setPair("OLA");
		alert2.setLimit(new BigDecimal(234.5));

		Alert alert3 = new Alert();
		alert3.setPair("ELA");
		alert3.setLimit(new BigDecimal(345.6));

		// When
		alertStore.add(alert1);
		alertStore.add(alert2);
		alertStore.add(alert3);
		String[] amount = alertStore.toString().split("pair");

		// Then
		assertEquals(amount.length - 1, alertStore.size());
	}

	@Test
	public void shouldContainAlert() {
		// Given
		// When
		alertStore.add(alert1);
		alertStore.add(alert2);
		alertStore.add(alert3);

		// Then
		assertEquals(true, alertStore.contains(alert1.getPair()));
	}

	@Test
	public void shouldNotContainAlert() {
		// Given
		// When
		alertStore.add(alert1);
		alertStore.add(alert3);

		// Then
		assertEquals(false, alertStore.contains(alert2.getPair()));
	}

	@Test
	public void shouldNotInCaseEmptyStoreAlert() {
		// Given
		// When
		// Then
		assertEquals(false, alertStore.contains(alert2.getPair()));
	}

}
