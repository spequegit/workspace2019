package archive02;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SensorController extends Thread {

	private int accuracy = 5;
	private final Pin pinTrigger = RaspiPin.GPIO_30; // trigger
	private final Pin pinEcho = RaspiPin.GPIO_31; // echo
	private double distance;

	public SensorController() throws InterruptedException {

	}

	@Override
	public void run() {
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(pinTrigger, "Sensor Trigger", PinState.LOW);
		GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(pinEcho, "Sensor Result", PinPullResistance.PULL_DOWN);
		Sensor sensor = new Sensor(trigger, echo);
		List<Double> distanceList = new ArrayList<>();

		while (true) {
			double distance = sensor.calculateDistance();
			distanceList = addFirstRemoveLast(distanceList, distance);
			this.distance = round(distanceList);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private List<Double> addFirstRemoveLast(List<Double> distanceList, double distance) {

		if (distanceList.size() == accuracy) {
			List<Double> result = new ArrayList<>();
			result.add(distance);
			result.addAll(distanceList.subList(0, accuracy - 1));
			return result;
		} else {
			distanceList.add(distance);
			return distanceList;
		}
	}

	private double round(List<Double> distanceList) {
		double roundDistance = 0;
		for (Double dis : distanceList) {
			roundDistance = roundDistance + dis;
		}
		return roundDistance / accuracy;
	}

	public boolean closerThan(int distance) {
		return this.distance < distance;
	}
}
