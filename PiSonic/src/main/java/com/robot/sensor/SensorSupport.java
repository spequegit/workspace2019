package com.robot.sensor;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

public class SensorSupport {

	private PinSensorDs pinSensorDs;
	// private int size = 5;
	private GpioPinDigitalOutput trigger;
	private GpioPinDigitalInput echo;
	private double distance = 0;

	List<Double> list = new ArrayList<>();
	private Sensor sensor;

	// List<Double> listFront = new ArrayList<>();

	public SensorSupport(Direction direction, GpioController gpio) {
		pinSensorDs = new PinSensorDs();
		trigger = gpio.provisionDigitalOutputPin(pinSensorDs.getPinTrigger(direction), PinState.LOW);
		echo = gpio.provisionDigitalInputPin(pinSensorDs.getPinEcho(direction), PinPullResistance.PULL_DOWN);
		sensor = new Sensor(trigger, echo);
	}

	public Double calculateDistance() throws InterruptedException {
		new Thread() {
			@Override
			public void run() {
				// addDistance(sensor.calculateDistance());
				// distance = average(list);
				distance = sensor.calculateDistance();
			}
		}.start();

		return distance;
	}

	// private void addDistance(double distance) {
	// if (list.size() == size) {
	// list.remove(0);
	// list.add(distance);
	// } else {
	// list.add(distance);
	// }
	// }
	//
	// private double average(List<Double> distanceList) {
	// double distance = 0;
	// for (Double dis : distanceList) {
	// distance = distance + dis;
	// }
	// double round = distance / list.size();
	// return Math.round(round);
	// }
}
