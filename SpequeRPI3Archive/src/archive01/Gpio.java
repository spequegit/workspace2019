package archive01;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Gpio {

	Map<Pin, GpioPinDigitalOutput> map = new HashMap<Pin, GpioPinDigitalOutput>();

	public Gpio(String[] arg) throws InterruptedException, IOException {
		final GpioController gpio = GpioFactory.getInstance();
		Pin[] allPins = RaspiPin.allPins();
		for (Pin pin : allPins) {
			GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin);
			map.put(pin, gpioPin);
		}

		GpioPinDigitalOutput pin = map.get(findPinByName("GPIO " + arg[0]));
		pin.setState(pin.isLow());
		System.out.println(pin.getName() + " " + pin.isHigh());
	}

	private Pin findPinByName(String gpioName) {
		Pin[] allPins = RaspiPin.allPins();
		for (Pin pin : allPins) {
			if (pin.getName().equals(gpioName)) {
				return pin;
			}
		}
		return null;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		new Gpio(args);
	}
}
