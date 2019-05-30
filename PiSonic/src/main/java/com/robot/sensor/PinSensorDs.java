package com.robot.sensor;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class PinSensorDs {
	private final Pin pinTriggerFront = RaspiPin.GPIO_00;
	private final Pin pinTriggerLeft = RaspiPin.GPIO_02;
	private final Pin pinTriggerRight = RaspiPin.GPIO_03;
	private final Pin pinEchoFront = RaspiPin.GPIO_23;
	private final Pin pinEchoLeft = RaspiPin.GPIO_24;
	private final Pin pinEchoRight = RaspiPin.GPIO_25;

	public Pin getPinTrigger(Direction direction) {
		switch (direction) {
		case FRONT:
			return pinTriggerFront;
		case LEFT:
			return pinTriggerLeft;
		case RIGHT:
			return pinTriggerRight;
		default:
			break;
		}
		return null;
	}

	public Pin getPinEcho(Direction direction) {
		switch (direction) {
		case FRONT:
			return pinEchoFront;
		case LEFT:
			return pinEchoLeft;
		case RIGHT:
			return pinEchoRight;
		default:
			break;
		}
		return null;
	}
}
