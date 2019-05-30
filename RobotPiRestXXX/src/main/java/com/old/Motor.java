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
public class Motor {

	private static final int PWM_PIN_A = 1;
	private static final int PWM_PIN_B = 26;
	private static final int PWM_PIN_C = 24;
	private static final int PWM_PIN_D = 23;

	private final Pin dir_A = RaspiPin.GPIO_00;
	private final Pin dir_B = RaspiPin.GPIO_22;
	private final Pin dir_C = RaspiPin.GPIO_27;
	private final Pin dir_D = RaspiPin.GPIO_07;

	private GpioPinDigitalOutput dirPin_A;
	private GpioPinDigitalOutput dirPin_B;
	private GpioPinDigitalOutput dirPin_C;
	private GpioPinDigitalOutput dirPin_D;

	Map<Pin, GpioPinDigitalOutput> map = new HashMap<Pin, GpioPinDigitalOutput>();

	public void dimCheck() throws InterruptedException, IOException {
		// Gpio.wiringPiSetup();
		final GpioController gpio = GpioFactory.getInstance();
		dirPin_A = gpio.provisionDigitalOutputPin(dir_A);
		dirPin_B = gpio.provisionDigitalOutputPin(dir_B);
		dirPin_C = gpio.provisionDigitalOutputPin(dir_C);
		dirPin_D = gpio.provisionDigitalOutputPin(dir_D);
		SoftPwm.softPwmCreate(PWM_PIN_A, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_B, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_C, 0, 100);
		SoftPwm.softPwmCreate(PWM_PIN_D, 0, 100);

		while (true) {
			int speed = 0;
			System.out.print("motor: ");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			String mtr = br1.readLine();
			if (mtr.equals("0")) {
				stop();
			} else {
				System.out.print("speed: ");
				BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
				String spd = br2.readLine();
				speed = 100 - Integer.parseInt(spd);
			}

			if (mtr.equals("0")) {
				runMotor(PWM_PIN_A, dirPin_A, speed);
			} else if (mtr.equals("1")) {
				runMotor(PWM_PIN_B, dirPin_B, speed);
			} else if (mtr.equals("2")) {
				runMotor(PWM_PIN_C, dirPin_C, speed);
			} else if (mtr.equals("3")) {
				runMotor(PWM_PIN_D, dirPin_D, speed);
			} else if (mtr.equals("f")) {
				runMotor(PWM_PIN_A, dirPin_A, speed);
				runMotor(PWM_PIN_B, dirPin_B, speed);
				runMotor(PWM_PIN_C, dirPin_C, speed);
				runMotor(PWM_PIN_D, dirPin_D, speed);
			} else if (mtr.equals("b")) {
				runMotor(PWM_PIN_A, dirPin_A, -(100 - speed));
				runMotor(PWM_PIN_B, dirPin_B, -(100 - speed));
				runMotor(PWM_PIN_C, dirPin_C, -(100 - speed));
				runMotor(PWM_PIN_D, dirPin_D, -(100 - speed));
			} else if (mtr.equals("l")) {
				runMotor(PWM_PIN_A, dirPin_A, speed);
				runMotor(PWM_PIN_B, dirPin_B, speed);
				runMotor(PWM_PIN_C, dirPin_C, -(100 - speed));
				runMotor(PWM_PIN_D, dirPin_D, -(100 - speed));
			} else if (mtr.equals("r")) {
				runMotor(PWM_PIN_A, dirPin_A, -(100 - speed));
				runMotor(PWM_PIN_B, dirPin_B, -(100 - speed));
				runMotor(PWM_PIN_C, dirPin_C, speed);
				runMotor(PWM_PIN_D, dirPin_D, speed);
			}

			System.out.println(dirPin_A.getState());
			System.out.println(dirPin_B.getState());
			System.out.println(dirPin_C.getState());
			System.out.println(dirPin_D.getState());
		}
	}

	private void runMotor(int pwmPin, GpioPinDigitalOutput dirPin, int speed) throws IOException {
		System.out.println(dirPin.getName() + ":" + speed);
		SoftPwm.softPwmWrite(pwmPin, Math.abs(speed));
		dirPin.setState(speed > 0);
	}

	private void stop() {
		System.out.println("STOP!");

		dirPin_A.setState(false);
		dirPin_B.setState(false);
		dirPin_C.setState(false);
		dirPin_D.setState(false);

		SoftPwm.softPwmWrite(PWM_PIN_A, 0);
		SoftPwm.softPwmWrite(PWM_PIN_B, 0);
		SoftPwm.softPwmWrite(PWM_PIN_C, 0);
		SoftPwm.softPwmWrite(PWM_PIN_D, 0);
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("started...");
		// new LedPwmController().dim(1);
		new Motor().dimCheck();
		// new LedPwmController().dim(100);
		System.out.println("bye...");
	}
}