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
	private int accuracy = 3;
	private GpioPinDigitalOutput trigger;
	private GpioPinDigitalInput echo;

	List<Double> list = new ArrayList<>();
	private Sensor sensor;
	private Object triggerFront;
	private Object echoFront;
	private Object triggerLeft;
	private Object echoLeft;
	private Object triggerRight;
	private Object echoRight;

	public SensorSupport(GpioPinDigitalOutput trigger, GpioPinDigitalInput echo) {
		this.trigger = trigger;
		this.echo = echo;
	}

	public void createSensor() {
		sensor = new Sensor(trigger, echo);
	}

	public double calculateDistance(Direction direction) {
		return sensor.calculateDistance();
	}

	public List<Double> addFirstRemoveLast(double distance) {
		if (list.size() == accuracy) {
			List<Double> result = new ArrayList<>();
			result.add(distance);
			result.addAll(list.subList(0, accuracy - 1));
			return result;
		} else {
			list.add(distance);
			return list;
		}
	}

	private double round(List<Double> distanceList) {
		double roundDistance = 0;
		for (Double dis : distanceList) {
			roundDistance = roundDistance + dis;
		}
		return roundDistance / accuracy;
	}

	public void createPins(final GpioController gpio) {
		triggerFront = gpio.provisionDigitalOutputPin(pinSensorDs.getPinTriggerFront(), PinState.LOW);
		echoFront = gpio.provisionDigitalInputPin(pinSensorDs.getPinEchoFront(), PinPullResistance.PULL_DOWN);
		triggerLeft = gpio.provisionDigitalOutputPin(pinSensorDs.getPinTriggerLeft(), PinState.LOW);
		echoLeft = gpio.provisionDigitalInputPin(pinSensorDs.getPinEchoLeft(), PinPullResistance.PULL_DOWN);
		triggerRight = gpio.provisionDigitalOutputPin(pinSensorDs.getPinTriggerRight(), PinState.LOW);
		echoRight = gpio.provisionDigitalInputPin(pinSensorDs.getPinEchoRight(), PinPullResistance.PULL_DOWN);
	}

}
