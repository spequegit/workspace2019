package archive01;

import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class ClockPi {

	private final Pin red = RaspiPin.GPIO_00;
	private final Pin blue = RaspiPin.GPIO_06;
	private final Pin yellow = RaspiPin.GPIO_21;
	private final Pin green = RaspiPin.GPIO_24;
	private GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput r;
	private GpioPinDigitalOutput g;
	private GpioPinDigitalOutput b;
	private GpioPinDigitalOutput y;

	public ClockPi() throws InterruptedException {
		r = gpio.provisionDigitalOutputPin(red);
		g = gpio.provisionDigitalOutputPin(green);
		b = gpio.provisionDigitalOutputPin(blue);
		y = gpio.provisionDigitalOutputPin(yellow);

		while (true) {
			Date date = new Date();
			int s = date.getSeconds();
			Thread.sleep(100);
			String binaryString = Integer.toBinaryString(s);
			show("000" + binaryString);
		}

	}

	private void show(String bin) {
		r.setState(bin.charAt(bin.length() - 4) == '1');
		b.setState(bin.charAt(bin.length() - 3) == '1');
		y.setState(bin.charAt(bin.length() - 2) == '1');
		g.setState(bin.charAt(bin.length() - 1) == '1');
	}

	public static void main(String[] args) throws InterruptedException {
		new ClockPi();
	}
}
