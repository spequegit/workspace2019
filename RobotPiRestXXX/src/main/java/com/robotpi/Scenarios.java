package com.robotpi;

import java.io.IOException;

public class Scenarios {

	private Motor motorFR;
	private Motor motorRR;
	private Motor motorRL;
	private Motor motorFL;
	private int speed;

	public Scenarios(Motor motorFR, Motor motorRR, Motor motorRL, Motor motorFL, int speed) {
		this.motorFR = motorFR;
		// TODO Auto-generated constructor stub
		this.motorRR = motorRR;
		this.motorRL = motorRL;
		this.motorFL = motorFL;
		this.setSpeed(speed);
	}

	public void drawRectangle() throws IOException, InterruptedException {
		go(getSpeed());
		Thread.sleep(1000);

		turnLeft();
		Thread.sleep(1000);

		go(getSpeed());
		Thread.sleep(1000);

		turnLeft();
		Thread.sleep(1000);

		go(getSpeed());
		Thread.sleep(1000);

		turnLeft();
		Thread.sleep(1000);

		go(getSpeed());
		Thread.sleep(1000);

		turnLeft();
		Thread.sleep(1000);
	}

	public void testRobot() throws IOException, InterruptedException {

		int reverseSpeed = -(100 - getSpeed());

		System.out.println(getSpeed());
		System.out.println(reverseSpeed);

		motorFR.go(getSpeed());
		Thread.sleep(500);
		motorFR.go(0);
		Thread.sleep(500);
		motorFR.go(reverseSpeed);
		Thread.sleep(500);
		motorFR.go(0);
		Thread.sleep(500);

		motorRR.go(getSpeed());
		Thread.sleep(500);
		motorRR.go(0);
		Thread.sleep(500);
		motorRR.go(reverseSpeed);
		Thread.sleep(500);
		motorRR.go(0);
		Thread.sleep(500);

		motorFL.go(getSpeed());
		Thread.sleep(500);
		motorFL.go(0);
		Thread.sleep(500);
		motorFL.go(reverseSpeed);
		Thread.sleep(500);
		motorFL.go(0);
		Thread.sleep(500);

		motorRL.go(getSpeed());
		Thread.sleep(500);
		motorRL.go(0);
		Thread.sleep(500);
		motorRL.go(reverseSpeed);
		Thread.sleep(500);
		motorRL.go(0);
		Thread.sleep(500);
	}

	public void turnRight() throws IOException {
		int reverseSpeed = -(100 - getSpeed());
		motorFR.go(reverseSpeed);
		motorRR.go(reverseSpeed);
		motorRL.go(speed);
		motorFL.go(speed);
	}

	public void turnLeft() throws IOException {
		int reverseSpeed = -(100 - getSpeed());
		motorFR.go(speed);
		motorRR.go(speed);
		motorRL.go(reverseSpeed);
		motorFL.go(reverseSpeed);
	}

	public void go(int speed) throws IOException {
		motorFR.go(speed);
		motorRR.go(speed);
		motorFL.go(speed);
		motorRL.go(speed);
	}

	public void left(int speed) throws IOException {
		motorFR.go(speed);
		motorRR.go(speed);
		motorFL.go(0);
		motorRL.go(0);
	}

	public void right(int speed) throws IOException {
		motorFR.go(0);
		motorRR.go(0);
		motorFL.go(speed);
		motorRL.go(speed);
	}

	public void stop() throws IOException {
		motorFR.go(0);
		motorRR.go(0);
		motorFL.go(0);
		motorRL.go(0);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
