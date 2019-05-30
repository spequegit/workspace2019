package archive01;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class TestStep {

	private final Pin pin0 = RaspiPin.GPIO_00;
	private final Pin pin25 = RaspiPin.GPIO_25;
	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput gpio0;
	private GpioPinDigitalOutput gpio25;

	public TestStep() throws InterruptedException {
		gpio0 = controller.provisionDigitalOutputPin(pin0);
		gpio25 = controller.provisionDigitalOutputPin(pin25);

		boolean direction = false;
		while (true) {
			step(200, 1, direction);
			Thread.sleep(1000);
			step(200, 2, direction);
			Thread.sleep(1000);
			step(200, 5, direction);
			Thread.sleep(1000);
			step(50, 1, direction);
			step(50, 1, !direction);
			step(50, 2, direction);
			step(50, 2, direction);
			step(50, 10, !direction);
			step(50, 10, direction);
			Thread.sleep(1000);
			direction = !direction;
			System.out.print(".");
		}
	}

	private void step(int i, int k, boolean b) throws InterruptedException {
		gpio25.setState(b);
		for (int j = 0; j < i; j++) {
			gpio0.setState(true);
			Thread.sleep(k);
			gpio0.setState(false);
			Thread.sleep(k);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new TestStep();
	}
}
