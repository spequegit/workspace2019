package com.pipwm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class LedPwmControllerOld {

	private static final int PIN01 = 1;
	private final Pin dir = RaspiPin.GPIO_00;

	Map<Pin, GpioPinDigitalOutput> map = new HashMap<Pin, GpioPinDigitalOutput>();

	public void dim(int times) throws InterruptedException {
		// initialize wiringPi library, this is needed for PWM
		Gpio.wiringPiSetup();
		// softPwmCreate(int pin, int value, int range)
		// the range is set like (min=0 ; max=100)
		int j = 100;
		SoftPwm.softPwmCreate(PIN01, 0, j);
		int counter = 0;
		while (counter < times) {
			// fade LED to fully ON
			for (int i = 0; i <= j; i++) {
				// softPwmWrite(int pin, int value)
				// This updates the PWM value on the given pin. The value is
				// checked to be in-range and pins
				// that haven't previously been initialized via softPwmCreate
				// will be silently ignored.
				SoftPwm.softPwmWrite(PIN01, i);
				Thread.sleep(10);
			}
			// fade LED to fully OFF
			for (int i = j; i >= 0; i--) {
				SoftPwm.softPwmWrite(PIN01, i);
				Thread.sleep(10);
			}
			counter++;
		}
	}

	public void dimCheck() throws InterruptedException, IOException {
		Gpio.wiringPiSetup();

		while (true) {
			System.out.println("0-100: ");
			SoftPwm.softPwmCreate(PIN01, 0, 100);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			SoftPwm.softPwmWrite(PIN01, Integer.parseInt(in));
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("started...");
		// new LedPwmController().dim(1);
		new LedPwmControllerOld().dimCheck();
		// new LedPwmController().dim(100);
		System.out.println("bye...");
	}
}
