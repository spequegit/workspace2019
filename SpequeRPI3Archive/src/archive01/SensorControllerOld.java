package archive01;

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
import com.robot001.Sensor;

public class SensorControllerOld {

	private int accuracy = 5;
	private final Pin pinBuzzer = RaspiPin.GPIO_00; // buzzer
	private final Pin pinTrigger = RaspiPin.GPIO_25; // trigger
	private final Pin pinEcho = RaspiPin.GPIO_24; // echo

	public SensorControllerOld(String[] arg) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput beep = gpio.provisionDigitalOutputPin(pinBuzzer);
		GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(pinTrigger, PinState.LOW);
		GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(pinEcho, PinPullResistance.PULL_DOWN);

		Sensor rangeSensor = new Sensor(trigger, echo);
		List<Double> distanceList = new ArrayList<>();

		if (arg.length > 0) {
			accuracy = Integer.parseInt(arg[0]);
		}

		while (true) {
			double distance = rangeSensor.calculateDistance();
			distanceList = addFirstRemoveLast(distanceList, distance);
			double roundDistance = round(distanceList);
			if (roundDistance < 150) {
				beep(beep);
				System.out.println(distance + " " + roundDistance);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (roundDistance < 5) {
				System.out.println("Bye");
				break;
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

	private void beep(GpioPinDigitalOutput beep) throws InterruptedException {
		beep.setState(true);
		Thread.sleep(10);
		beep.setState(false);
	}

	public static void main(String[] args) throws InterruptedException {
		new SensorControllerOld(args);
	}
}
