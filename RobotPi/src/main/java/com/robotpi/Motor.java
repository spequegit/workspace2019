package com.robotpi;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.wiringpi.SoftPwm;

public class Motor {
	private int pwmPin;
	private Pin directionPin;
	private GpioPinDigitalOutput directionGpio;

	public Motor(int pwmPin, Pin directionPin) {
		this.pwmPin = pwmPin;
		this.directionPin = directionPin;
		initGpio();
	}

	private void initGpio() {
		final GpioController gpio = GpioFactory.getInstance();
		directionGpio = gpio.provisionDigitalOutputPin(directionPin);
		SoftPwm.softPwmCreate(pwmPin, 0, 100);
	}

	public void setDirection(boolean direction) {
		directionGpio.setState(direction);
	}

	private void setSpeed(int speed) {
		SoftPwm.softPwmWrite(pwmPin, Math.abs(speed));
	}

	public void go(int speed) throws IOException {
		setSpeed(Math.abs(speed));
		setDirection(speed > 0);
	}

}
