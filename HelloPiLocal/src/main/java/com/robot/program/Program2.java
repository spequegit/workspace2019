package com.robot.program;

import com.robot.motor.MotorController;

public class Program2 extends Thread {

	private static final int TIME = 300;
	private MotorController controller;
	private static final int SLEEP = 4;

	public Program2(MotorController controller) {
		this.controller = controller;
	}

	public void run() {
		try {
			Thread.sleep(1000);
			for (int i = 0; i < 5; i++) {
				controller.rightWheel(SLEEP);
				Thread.sleep(TIME);
				controller.leftWheel(SLEEP);
				Thread.sleep(TIME);
				controller.rightWheel(-SLEEP);
				Thread.sleep(TIME);
				controller.leftWheel(-SLEEP);
				Thread.sleep(TIME);
			}
			controller.stop();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
