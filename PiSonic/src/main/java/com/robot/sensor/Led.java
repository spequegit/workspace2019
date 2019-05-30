package com.robot.sensor;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Led {
	private GpioPinDigitalOutput led;

	public Led(GpioPinDigitalOutput led) {
		this.led = led;
	}

	public void turnOn() {
		led.high();
	}

	public void turnOff() {
		led.low();
	}
}