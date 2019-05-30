package archive01;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Step {

	private final Pin pin0 = RaspiPin.GPIO_00;
	private final Pin pin25 = RaspiPin.GPIO_25;

	public Step(String[] arg) throws InterruptedException {
		System.out.println("mili nano times direction");
		GpioController controller = GpioFactory.getInstance();
		GpioPinDigitalOutput gpio = controller.provisionDigitalOutputPin(pin0);
		GpioPinDigitalOutput direction = controller.provisionDigitalOutputPin(pin25);
		int mili = Integer.parseInt(arg[0]);
		int nano = Integer.parseInt(arg[1]);
		int times = Integer.parseInt(arg[2]);
		direction.setState(arg[3].equals("1"));

		System.out.print("start");
		for (int i = 0; i < times; i++) {
			gpio.setState(true);
			Thread.sleep(mili, nano);
			gpio.setState(false);
			Thread.sleep(mili, nano);
			// 1 mili - 1000000 nano
		}
		System.out.print("end");
	}

	public static void main(String[] args) throws InterruptedException {
		new Step(args);
	}
}
