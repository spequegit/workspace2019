package com.robot.sensor;

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

	private int accuracy = 3;
	private double distance1;
	private double distance2;
	private double distance3;

	private final Pin pinTriggerCenter = RaspiPin.GPIO_08;
	private final Pin pinTriggerLeft = RaspiPin.GPIO_08;
	private final Pin pinTriggerRight = RaspiPin.GPIO_08;
	private final Pin pinEchoCenter = RaspiPin.GPIO_24;
	private final Pin pinEchoLeft = RaspiPin.GPIO_24;
	private final Pin pinEchoRight = RaspiPin.GPIO_24;
	private GpioPinDigitalOutput triggerCenter;
	private GpioPinDigitalOutput triggerLeft;
	private GpioPinDigitalOutput triggerRight;
	private GpioPinDigitalInput echoCenter;
	private GpioPinDigitalInput echoLeft;
	private GpioPinDigitalInput echoRight;

	public SensorController() throws InterruptedException {

	}

	@Override
	public void run() {
		final GpioController gpio = GpioFactory.getInstance();
		createPins(gpio);
		Sensor sensor1 = new Sensor(triggerCenter, echoCenter);
		Sensor sensor2 = new Sensor(triggerLeft, echoLeft);
		Sensor sensor3 = new Sensor(triggerRight, echoRight);

		List<Double> distanceList1 = new ArrayList<>();
		List<Double> distanceList2 = new ArrayList<>();
		List<Double> distanceList3 = new ArrayList<>();

		while (true) {
			try {
				double d1 = sensor1.calculateDistance();
				Thread.sleep(10);
				double d2 = sensor2.calculateDistance();
				Thread.sleep(10);
				double d3 = sensor3.calculateDistance();
				Thread.sleep(10);

				distanceList1 = addFirstRemoveLast(distanceList1, d1);
				distanceList2 = addFirstRemoveLast(distanceList2, d2);
				distanceList3 = addFirstRemoveLast(distanceList3, d3);

				this.distance1 = round(distanceList1);
				this.distance2 = round(distanceList2);
				this.distance3 = round(distanceList3);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createPins(final GpioController gpio) {
		triggerCenter = gpio.provisionDigitalOutputPin(pinTriggerCenter, PinState.LOW);
		echoCenter = gpio.provisionDigitalInputPin(pinEchoCenter, PinPullResistance.PULL_DOWN);
		triggerLeft = gpio.provisionDigitalOutputPin(pinTriggerLeft, PinState.LOW);
		echoLeft = gpio.provisionDigitalInputPin(pinEchoLeft, PinPullResistance.PULL_DOWN);
		triggerRight = gpio.provisionDigitalOutputPin(pinTriggerRight, PinState.LOW);
		echoRight = gpio.provisionDigitalInputPin(pinEchoRight, PinPullResistance.PULL_DOWN);
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

	public double getDistance1() {
		return distance1;
	}

	public double getDistance2() {
		return distance2;
	}

	public double getDistance3() {
		return distance3;
	}

	public int getDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

}
