package com.robot.program;

import com.robot.motor.MotorController;
import com.robot.sensor.SensorController;

public class Program1 {

	private static final int MINIMUM = 50;
	private SensorController controller;
	private MotorController movementController;

	public Program1() {
		try {
			controller = new SensorController();
			controller.start();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		movementController = new MotorController();
		new Thread() {
			public void run() {
				try {
					scan();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	public void scan() throws InterruptedException {
		while (true) {
			// System.out.println(controller.getDistance());
			Thread.sleep(100);
			if (tooClose()) {
				System.out.println("stop!");
				movementController.stop();
				Thread.sleep(500);
				if (tooClose()) {
					movementController.backward(4);
					Thread.sleep(1000);
					movementController.left(4);
					Thread.sleep(500);
					while (tooClose()) {
						movementController.left(4);
						Thread.sleep(100);
					}
				}
				Thread.sleep(100);
				movementController.stop();
				Thread.sleep(500);
				scan();
				break;
			} else {
				movementController.forward(2);
			}
		}
	}

	private boolean tooClose() {
		return controller.getDistance() < MINIMUM;
	}
}
