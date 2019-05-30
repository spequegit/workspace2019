package archive02;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Sensor {

	private GpioPinDigitalOutput trigger;
	private GpioPinDigitalInput echo;

	public Sensor(GpioPinDigitalOutput trigger, GpioPinDigitalInput echo) {
		this.trigger = trigger;
		this.echo = echo;
	}

	public double calculateDistance() {
		long start = 0;
		trigger();
		while (echo.isLow()) {
			start = System.nanoTime();
		}
		while (echo.isHigh()) { // wait for low
		}
		return (System.nanoTime() - start) / 58000;
	}

	private void trigger() {
		try {
			trigger.high();
			Thread.sleep(10);
			trigger.low();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}