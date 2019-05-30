package archive02;

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

public class LedController {

	private final Pin red = RaspiPin.GPIO_00;
	private final Pin blue = RaspiPin.GPIO_06;
	private final Pin yellow = RaspiPin.GPIO_21;
	private final Pin green = RaspiPin.GPIO_24;
	Map<Pin, GpioPinDigitalOutput> map = new HashMap<Pin, GpioPinDigitalOutput>();

	public LedController(String[] arg) throws InterruptedException, IOException {
		final GpioController gpio = GpioFactory.getInstance();
		Pin[] allPins = RaspiPin.allPins();
		for (Pin pin : allPins) {
			GpioPinDigitalOutput gpioPin = gpio.provisionDigitalOutputPin(pin);
			map.put(pin, gpioPin);
		}

		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			if (in.equals("power")) {
				power(map, true, 20);
			} else if (in.equals("exit")) {
				break;
			} else if (in.equals("test")) {
				power(map, true, 700);
			} else if (in.equals("demo")) {
				demo();
			} else {
				GpioPinDigitalOutput pin = map.get(findPinByName("GPIO " + in));
				pin.setState(pin.isLow());
				power(map, false, 20);
			}
		}
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

	private void demo() throws InterruptedException {
		GpioPinDigitalOutput gpioRed = map.get(red);
		GpioPinDigitalOutput gpioGreen = map.get(green);
		GpioPinDigitalOutput gpioYellow = map.get(yellow);
		GpioPinDigitalOutput gpioBlue = map.get(blue);
		boolean power = true;
		for (int i = 0; i < 50; i++) {
			int sleep = 50;
			setLed(gpioRed, power, sleep);
			setLed(gpioBlue, power, sleep);
			setLed(gpioYellow, power, sleep);
			setLed(gpioGreen, power, sleep);
			power = !power;
		}

	}

	private void setLed(GpioPinDigitalOutput gpioGreen, boolean power, int sleep) throws InterruptedException {
		gpioGreen.setState(power);
		Thread.sleep(sleep);
	}

	private void power(Map<Pin, GpioPinDigitalOutput> map, boolean power, int speed) throws InterruptedException {
		int i = 0;
		for (GpioPinDigitalOutput gpioPin : map.values()) {
			if (power) {
				gpioPin.setState(gpioPin.isLow());
			}
			System.out.print(gpioPin.getName() + "\t" + (gpioPin.getState().isHigh() ? "*" : " ") + "\t");
			if (++i % 5 == 0) {
				System.out.println();
			}
			Thread.sleep(speed);
		}
		System.out.println();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Hello Pi GPIOs! v1.8");
		new LedController(args);
		System.out.println("Bye!");
	}
}
