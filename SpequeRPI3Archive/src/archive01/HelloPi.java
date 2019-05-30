package archive01;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class HelloPi {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello Raspberry!");

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 & #03 as an output pins and blink
		final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		final GpioPinDigitalOutput led2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
		int i = 0;
		while (i++ < Integer.parseInt(args[0])) {
			led1.setState(true);
			led2.setState(false);
			Thread.sleep(Integer.parseInt(args[1]));
			led1.setState(false);
			led2.setState(true);
			Thread.sleep(Integer.parseInt(args[1]));
			System.out.print(".");
		}

		led1.setState(false);
		led2.setState(false);
	}
}
