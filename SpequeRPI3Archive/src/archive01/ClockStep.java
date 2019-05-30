package archive01;

import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class ClockStep {

	private final Pin pin0 = RaspiPin.GPIO_00;
	private final Pin pin25 = RaspiPin.GPIO_25;
	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput gpio0;
	private GpioPinDigitalOutput gpio25;

	public ClockStep() throws InterruptedException {
		gpio0 = controller.provisionDigitalOutputPin(pin0);
		gpio25 = controller.provisionDigitalOutputPin(pin25);

		while (true) {
			Date date = new Date();
			int s = date.getSeconds();
			for (int i = 0; i <= s; i++) {
				step();
			}
			Thread.sleep(1000 - s);
			gpio25.setState(gpio25.isLow());
		}
	}

	private void step() throws InterruptedException {
		gpio0.setState(true);
		Thread.sleep(1);
		gpio0.setState(false);
		Thread.sleep(1);
	}

	public static void main(String[] args) throws InterruptedException {
		new ClockStep();
	}
}
