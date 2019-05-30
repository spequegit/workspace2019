package com.robot;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.exception.UnsupportedPinPullResistanceException;

public class RPI3Tester {

	public RPI3Tester() {
		searchPullDown();
	}

	private void searchPullDown() {
		System.out.println("searchPullDown");

		final GpioController gpio = GpioFactory.getInstance();
		Pin[] allPins = RaspiPin.allPins();
		for (Pin pin : allPins) {
			try {
				gpio.provisionDigitalInputPin(pin, "echo", PinPullResistance.PULL_DOWN);
				System.out.println(pin.getName() + "\t\t PullDown OK");
			} catch (UnsupportedPinPullResistanceException e) {
				System.out.println(pin.getName() + "\t\t -");
			}
		}

	}

	public static void main(String[] args) {
		new RPI3Tester();
	}
}
