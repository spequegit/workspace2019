package com.robot.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

public class SonicController extends Thread {

	private SensorSupport supportFront;
	private SensorSupport supportLeft;
	private SensorSupport supportRight;
	private Led led;

	public static void main(String[] args) {
		System.out.println("PiSonic-3");
		new SonicController().start();
	}

	public SonicController() {
		final GpioController gpio = GpioFactory.getInstance();
		supportFront = new SensorSupport(Direction.FRONT, gpio);
		supportLeft = new SensorSupport(Direction.LEFT, gpio);
		supportRight = new SensorSupport(Direction.RIGHT, gpio);

		led = new Led(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28));
	}

	@Override
	public void run() {
		System.out.println("run");
		while (true) {
			try {
				Double dr = supportRight.calculateDistance();
				// Thread.sleep(10);
				Double dl = supportLeft.calculateDistance();
				// Thread.sleep(10);
				Double df = supportFront.calculateDistance();
				Thread.sleep(100);

				int min = 10;
				if (dl < min || dr < min || df < min) {
					System.out.println("distance: " + dl + " < " + df + " > " + dr);
					led.turnOn();
				} else {
					led.turnOff();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
