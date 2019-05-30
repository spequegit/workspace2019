package com.robot.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class SensorController2 extends Thread {

	private SensorSupport sensorSupport;

	public SensorController2() throws InterruptedException {

	}

	@Override
	public void run() {
		final GpioController gpio = GpioFactory.getInstance();
		sensorSupport.createPins(gpio);

		while (true) {
			try {
				double distanceFront = sensorSupport.calculateDistance(Direction.FRONT);
				Thread.sleep(10);
				double distanceLeft = sensorSupport.calculateDistance(Direction.FRONT);
				Thread.sleep(10);
				double distanceRight = sensorSupport.calculateDistance(Direction.FRONT);
				Thread.sleep(10);
				// listFront = sensorSupportF.addFirstRemoveLast(distanceFront);
				// listLeft = sensorSupportL.addFirstRemoveLast(distanceLeft);
				// listRight = sensorSupportR.addFirstRemoveLast(distanceRight);
				// this.distanceFront = round(listFront);
				// this.distanceLeft = round(listLeft);
				// this.distanceRight = round(listRight);
				Thread.sleep(100);

				System.out.print(distanceLeft + " < ");
				System.out.print(distanceFront + " > ");
				System.out.println(distanceRight);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
