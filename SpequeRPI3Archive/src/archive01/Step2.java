package archive01;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Step2 {

	private final Pin pin0 = RaspiPin.GPIO_00;
	private final Pin pin25 = RaspiPin.GPIO_25;

	public Step2(String[] arg) throws InterruptedException {
		GpioController controller = GpioFactory.getInstance();
		GpioPinDigitalOutput gpio = controller.provisionDigitalOutputPin(pin0);
		GpioPinDigitalOutput direction = controller.provisionDigitalOutputPin(pin25);
		int sleep = Integer.parseInt(arg[0]);
		int times = Integer.parseInt(arg[1]);

		slowStart(gpio, sleep);

		for (int i = 0; i < times; i++) {
			gpio.setState(true);
			Thread.sleep(sleep);
			gpio.setState(false);
			Thread.sleep(sleep);
		}

		slowStop(gpio, sleep);
		direction.setState(direction.isLow());
		Thread.sleep(1000);
		slowStart(gpio, sleep);

		for (int i = 0; i < times; i++) {
			gpio.setState(true);
			Thread.sleep(sleep);
			gpio.setState(false);
			Thread.sleep(sleep);
		}

		slowStop(gpio, sleep);
	}

	private void slowStop(GpioPinDigitalOutput gpio, int sleep) throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			gpio.setState(true);
			sleep(sleep, i);
			gpio.setState(false);
			sleep(sleep, i);
		}
	}

	private void slowStart(GpioPinDigitalOutput gpio, int sleep) throws InterruptedException {
		for (int i = 20; i > 0; i--) {
			gpio.setState(true);
			sleep(sleep, i);
			gpio.setState(false);
			sleep(sleep, i);
		}
	}

	private void sleep(int sleep, int i) throws InterruptedException {
		Thread.sleep(sleep + i);
	}

	public static void main(String[] args) throws InterruptedException {
		new Step2(args);
	}
}
