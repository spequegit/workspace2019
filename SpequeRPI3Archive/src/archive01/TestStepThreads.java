package archive01;

import java.util.Random;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class TestStepThreads {

	private final Pin pin1Dir = RaspiPin.GPIO_25;
	private final Pin pin2Dir = RaspiPin.GPIO_24;
	private final Pin pin1Step = RaspiPin.GPIO_00;
	private final Pin pin2Step = RaspiPin.GPIO_02;
	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput gpio1Dir;
	private GpioPinDigitalOutput gpio2Dir;
	private GpioPinDigitalOutput gpio1Step;
	private GpioPinDigitalOutput gpio2Step;

	public TestStepThreads() throws InterruptedException {
		gpio1Dir = controller.provisionDigitalOutputPin(pin1Dir);
		gpio2Dir = controller.provisionDigitalOutputPin(pin2Dir);
		gpio1Step = controller.provisionDigitalOutputPin(pin1Step);
		gpio2Step = controller.provisionDigitalOutputPin(pin2Step);

		new ScenarioRandom(gpio1Step, gpio1Dir).start();
		new ScenarioRandom(gpio2Step, gpio2Dir).start();
		// new ScenarioFast(gpio1Step).start();
		// new ScenarioFast(gpio2Step).start();
	}

	private boolean randomBoolean() {
		return new Random().nextBoolean();
	}

	private int random(int i) {
		return new Random().nextInt(i) + 1;
	}

	private void step(GpioPinDigitalOutput gpioStep, int steps, int sleep) throws InterruptedException {
		System.out.println(gpioStep.getName() + " steps:" + steps + " sleep:" + sleep);
		for (int j = 0; j < steps; j++) {
			gpioStep.setState(true);
			Thread.sleep(1);
			gpioStep.setState(false);
			Thread.sleep(1);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new TestStepThreads();
	}

	class ScenarioRandom extends Thread {
		private GpioPinDigitalOutput gpioDirection;
		private GpioPinDigitalOutput gpioStep;

		public ScenarioRandom(GpioPinDigitalOutput gpioStep, GpioPinDigitalOutput gpioDirection) {
			this.gpioStep = gpioStep;
			this.gpioDirection = gpioDirection;
		}

		@Override
		public void run() {
			while (true) {
				gpioDirection.setState(randomBoolean());
				try {
					step(gpioStep, random(500) + 1000, random(10));
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(gpioStep.getName() + " " + (gpioDirection.isHigh() ? "left" : "rigth"));
			}
		}
	}

	class ScenarioFast extends Thread {
		private GpioPinDigitalOutput gpioStep;

		public ScenarioFast(GpioPinDigitalOutput gpioStep) {
			this.gpioStep = gpioStep;
		}

		@Override
		public void run() {
			int nano = 999999;
			while (true) {
				try {
					gpioStep.setState(true);
					Thread.sleep(0, 10);
					gpioStep.setState(false);
					Thread.sleep(1, nano);
					if (nano > 0) {
						nano = nano - 1000;
					}
					if (nano < 1) {
						nano = 1;
					}
					System.out.println(nano);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ScenarioConst extends Thread {
		private GpioPinDigitalOutput gpioStep;

		public ScenarioConst(GpioPinDigitalOutput gpioStep) {
			this.gpioStep = gpioStep;
		}

		@Override
		public void run() {
			while (true) {
				try {
					gpioStep.setState(true);
					Thread.sleep(1);
					gpioStep.setState(false);
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
