package com.old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Hello world!
 *
 */
public class App {

	private static final int PWM_PIN_A = 1;
	private static final int PWM_PIN_B = 26;
	private static final int PWM_PIN_C = 24;
	private static final int PWM_PIN_D = 23;

	private final Pin dir_A = RaspiPin.GPIO_00;
	private final Pin dir_B = RaspiPin.GPIO_22;
	private final Pin dir_C = RaspiPin.GPIO_27;
	private final Pin dir_D = RaspiPin.GPIO_25;

	Map<Pin, GpioPinDigitalOutput> map = new HashMap<Pin, GpioPinDigitalOutput>();

	public void dimCheck() throws InterruptedException, IOException {
		// Gpio.wiringPiSetup();
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput dirPin_A = gpio.provisionDigitalOutputPin(dir_A);
		GpioPinDigitalOutput dirPin_B = gpio.provisionDigitalOutputPin(dir_B);

		GpioPinDigitalOutput dirPin_C = gpio.provisionDigitalOutputPin(dir_C);
		GpioPinDigitalOutput dirPin_D = gpio.provisionDigitalOutputPin(dir_D);
		boolean dirA = false;
		boolean dirB = false;
		boolean dirC = false;
		boolean dirD = false;
		int speed = 0;
		while (true) {
			System.out.println("0-100 abcd: ");
			SoftPwm.softPwmCreate(PWM_PIN_A, 0, 100);
			SoftPwm.softPwmCreate(PWM_PIN_B, 0, 100);
			SoftPwm.softPwmCreate(PWM_PIN_C, 0, 100);
			SoftPwm.softPwmCreate(PWM_PIN_D, 0, 100);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			if (in.equals("a")) {
				dirA = !dirA;
				System.out.println("A:" + dirA);
			} else if (in.equals("b")) {
				dirB = !dirB;
				System.out.println("B:" + dirB);
			} else if (in.equals("c")) {
				dirC = !dirC;
				System.out.println("C:" + dirC);
			} else if (in.equals("d")) {
				dirD = !dirD;
				System.out.println("D:" + dirD);
			} else {
				speed = Integer.parseInt(in);
			}
			dirPin_A.setState(dirA);
			dirPin_B.setState(dirB);
			dirPin_C.setState(dirC);
			dirPin_D.setState(dirD);
			SoftPwm.softPwmWrite(PWM_PIN_A, Math.abs(speed - (dirA ? 100 : 0)));
			SoftPwm.softPwmWrite(PWM_PIN_B, Math.abs(speed - (dirB ? 100 : 0)));
			SoftPwm.softPwmWrite(PWM_PIN_C, Math.abs(speed - (dirC ? 100 : 0)));
			SoftPwm.softPwmWrite(PWM_PIN_D, Math.abs(speed - (dirD ? 100 : 0)));
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("started...");
		// new LedPwmController().dim(1);
		new App().dimCheck();
		// new LedPwmController().dim(100);
		System.out.println("bye...");
	}
}
