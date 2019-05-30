package com.old;

import java.io.IOException;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class PiPwm4LedsDemo {

	private static final int PWM_PIN_A = 1;
	private static final int PWM_PIN_B = 26;
	private static final int PWM_PIN_C = 24;
	private static final int PWM_PIN_D = 23;

	public PiPwm4LedsDemo() throws InterruptedException, IOException {
		Gpio.wiringPiSetup();

		SoftPwm.softPwmCreate(PWM_PIN_A, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_B, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_C, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_D, 0, 100);

		dimCheck();
	}

	public void dimCheck() throws InterruptedException, IOException {
		int level = 0;
		boolean dir = false;
		int led = 0;
		while (level <= 100 && level >= 0) {
			if (level == 100) {
				dir = false;
			}
			if (level == 0) {
				dir = true;
				led++;
				if (led > 3)
					led = 0;
			}
			level = level + (dir ? 5 : -5);

			System.out.println(level);
//			if (led == 0)
			SoftPwm.softPwmWrite(PWM_PIN_A, level);
//			if (led == 1)
			SoftPwm.softPwmWrite(PWM_PIN_B, level);
//			if (led == 2)
			SoftPwm.softPwmWrite(PWM_PIN_C, level);
//			if (led == 3)
			SoftPwm.softPwmWrite(PWM_PIN_D, level);
			Thread.sleep(100);

		}

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("start");
		new PiPwm4LedsDemo();
	}
}
