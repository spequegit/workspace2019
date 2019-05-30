package archive01;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class StepController {

	private final Pin pin1Dir = RaspiPin.GPIO_25;
	private final Pin pin2Dir = RaspiPin.GPIO_24;
	private final Pin pin1Step = RaspiPin.GPIO_00;
	private final Pin pin2Step = RaspiPin.GPIO_02;
	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput gpio1Dir;
	private GpioPinDigitalOutput gpio2Dir;
	private GpioPinDigitalOutput gpio1Step;
	private GpioPinDigitalOutput gpio2Step;
	private boolean move;
	private boolean direction;
	private boolean move2;
	private boolean direction2;

	public StepController() throws InterruptedException {
		gpio1Dir = controller.provisionDigitalOutputPin(pin1Dir);
		gpio2Dir = controller.provisionDigitalOutputPin(pin2Dir);
		gpio1Step = controller.provisionDigitalOutputPin(pin1Step);
		gpio2Step = controller.provisionDigitalOutputPin(pin2Step);

		new Thread() {
			public void run() {
				while (true) {
					if (move) {
						try {
							gpio1Dir.setState(direction);
							step(gpio1Step);
							System.out.print(".");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					if (move2) {
						try {
							gpio2Dir.setState(direction2);
							step(gpio2Step);
							System.out.print(".");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
		}.start();
	}

	private void step(GpioPinDigitalOutput gpioStep) throws InterruptedException {
		gpioStep.setState(true);
		Thread.sleep(1);
		gpioStep.setState(false);
		Thread.sleep(1);
	}

	public void move(boolean direction) {
		this.move = true;
		this.direction = direction;
	}

	public void move2(boolean direction) {
		this.move2 = true;
		this.direction2 = direction;
	}

	public void stop() {
		this.move = false;
	}

	public void stop2() {
		this.move2 = false;
	}
}
