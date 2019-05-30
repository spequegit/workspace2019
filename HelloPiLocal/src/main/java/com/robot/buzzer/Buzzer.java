package com.robot.buzzer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Buzzer {

	private GpioPinDigitalOutput buzzerGpio;
	private boolean work;

	public Buzzer() {
		System.out.println("buzzer");
		GpioController controller = GpioFactory.getInstance();
		buzzerGpio = controller.provisionDigitalOutputPin(RaspiPin.GPIO_27);
		initialize();
	}

	public void initialize() {
		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("beep");
					while (true) {
						if (work) {
							buzzerGpio.setState(true);
							Thread.sleep(50);
							work = false;
						} else {
							buzzerGpio.setState(false);
						}
						Thread.sleep(50);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void horn() {
		this.work = true;
	}
}
