package com.pi.led;

import java.io.IOException;
import java.util.Random;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class LedPwm {

	private static final int PWMPIN = 1;

	public LedPwm() throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalOutput directionPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

		SoftPwm.softPwmCreate(PWMPIN, 0, 100);
		while (true) {
			SoftPwm.softPwmWrite(PWMPIN, new Random().nextInt(100));
			Thread.sleep(1000);
			directionPin.setState(new Random().nextBoolean());
			System.out.print("|");
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		new LedPwm();
	}
}
