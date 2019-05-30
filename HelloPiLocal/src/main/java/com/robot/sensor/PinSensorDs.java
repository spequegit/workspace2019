package com.robot.sensor;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class PinSensorDs {
	private final Pin pinTriggerFront = RaspiPin.GPIO_08;
	private final Pin pinTriggerLeft = RaspiPin.GPIO_08;
	private final Pin pinTriggerRight = RaspiPin.GPIO_08;
	private final Pin pinEchoFront = RaspiPin.GPIO_24;
	private final Pin pinEchoLeft = RaspiPin.GPIO_24;
	private final Pin pinEchoRight = RaspiPin.GPIO_24;

	public Pin getPinTriggerFront() {
		return pinTriggerFront;
	}

	public Pin getPinTriggerLeft() {
		return pinTriggerLeft;
	}

	public Pin getPinTriggerRight() {
		return pinTriggerRight;
	}

	public Pin getPinEchoFront() {
		return pinEchoFront;
	}

	public Pin getPinEchoLeft() {
		return pinEchoLeft;
	}

	public Pin getPinEchoRight() {
		return pinEchoRight;
	}
}
