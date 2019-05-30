package com.robot.motor;

import com.pi4j.io.gpio.RaspiPin;

public class MotorController {

	private int sleep = 2;
	private final Motor motorFL = new Motor(RaspiPin.GPIO_25, RaspiPin.GPIO_03);
	private final Motor motorFR = new Motor(RaspiPin.GPIO_21, RaspiPin.GPIO_02);
	private boolean inverted = false;

	private void move(int frontRight, int frontLeft) {
		motorFL.move(frontLeft);
		motorFR.move(frontRight);
	}

	public void stop() {
		System.out.println("stop");
		move(0, 0);
	}

	public void forward() {
		System.out.println("forward:" + sleep());
		move(sleep(), sleep());
	}

	public void backward() {
		System.out.println("backward:" + sleep());
		move(-sleep(), -sleep());
	}

	private int sleep() {
		return sleep * (inverted ? -1 : 1);
	}

	public void left() {
		System.out.println("left");
		move(3, -3);
	}

	public void right() {
		System.out.println("right");
		move(-3, 3);
	}

	public void forwardLeft() {
		System.out.println("forwardLeft");
		move(sleep() + 1 * (inverted ? -1 : 0), sleep() + 1 * (inverted ? 0 : 1));
	}

	public void forwardRight() {
		System.out.println("forwardRight");
		move(sleep() + 1 * (inverted ? 0 : 1), sleep() + 1 * (inverted ? -1 : 0));
	}

	public void backwardLeft() {
		System.out.println("backwardLeft");
		move(-sleep() - 1 * (inverted ? 0 : 1), -sleep() - 1 * (inverted ? -1 : 0));
	}

	public void backwardRight() {
		System.out.println("backwardRight");
		move(-sleep() - 1 * (inverted ? -1 : 0), -sleep() - 1 * (inverted ? 0 : 1));
	}

	public void slower() {
		sleep++;
		System.out.println(sleep());
	}

	public void faster() {
		sleep--;
		System.out.println(sleep());
	}

	public void forward(int i) {
		this.sleep = i;
		forward();
	}

	public void left(int i) {
		this.sleep = i;
		left();
	}

	public void backward(int i) {
		this.sleep = i;
		backward();
	}

	public void rightWheel(int i) {
		move(i, 0);
	}

	public void leftWheel(int i) {
		move(0, i);
	}

	public void changeDirection() {
		System.out.println("changeDirection");
		this.inverted = !inverted;
	}

}
