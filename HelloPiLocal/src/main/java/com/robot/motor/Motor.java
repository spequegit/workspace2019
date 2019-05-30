package com.robot.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Motor extends Thread {

	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput directionGpio;
	private GpioPinDigitalOutput stepGpio;
	private boolean work;
	private boolean direction;
	private long sleep = 5;

	public Motor(Pin stepPin, Pin directionPin) {
		directionGpio = controller.provisionDigitalOutputPin(directionPin);
		stepGpio = controller.provisionDigitalOutputPin(stepPin);
		start();
	}

	public void run() {
		while (true) {
			directionGpio.setState(direction);
			try {
				if (work) {
					step(stepGpio);
				} else {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void step(GpioPinDigitalOutput gpioStep) throws InterruptedException {
		gpioStep.setState(true);
		Thread.sleep(1);
		gpioStep.setState(false);
		Thread.sleep(sleep);
	}

	public void move(int sleep) {
		this.work = sleep != 0;
		this.direction = sleep >= 0;
		this.sleep = Math.abs(sleep);
	}
}
