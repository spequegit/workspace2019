package archive01;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Sensor {

	private GpioPinDigitalOutput trigger;
	private GpioPinDigitalInput echo;

	Sensor(GpioPinDigitalOutput trigger, GpioPinDigitalInput echo) {
		this.trigger = trigger;
		this.echo = echo;
	}

	/**
	 * Trigger the Range Sensor and return the result
	 * 
	 * @throws InterruptedException
	 */
	public double calculateDistance() throws InterruptedException {
		long start = 0;
		trigger();
		while (echo.isLow()) {
			start = System.nanoTime();
		}
		while (echo.isHigh()) {
			// wait for low
		}
		return (System.nanoTime() - start) / 58000;
	}

	private void trigger() throws InterruptedException {
		trigger.high();
		Thread.sleep(10);
		trigger.low();
	}
}