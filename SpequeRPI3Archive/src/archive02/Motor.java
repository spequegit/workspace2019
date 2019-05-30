package archive02;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Motor extends Thread {

	private GpioController controller = GpioFactory.getInstance();
	private GpioPinDigitalOutput directionGpio;
	private GpioPinDigitalOutput stepGpio;
	private boolean work;
	private boolean direction;
	private long speed = 5;

	public Motor(Pin stepPin, Pin directionPin) throws InterruptedException {
		directionGpio = controller.provisionDigitalOutputPin(directionPin);
		stepGpio = controller.provisionDigitalOutputPin(stepPin);
		start();
	}

	public void run() {
		// long time = System.currentTimeMillis();
		// int currentSpeed = 1;
		while (true) {
			directionGpio.setState(direction);
			if (work) {
				try {
					// if (System.currentTimeMillis() - time > 100) {
					// time = System.currentTimeMillis();
					// if (currentSpeed < speed) {
					// currentSpeed++;
					// }
					// }
					step(stepGpio, speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void showLog(int currentSpeed) {
		int[] sleep = Speed.sleep(currentSpeed);
		System.out.println(stepGpio.getName() + ": " + currentSpeed + "/" + speed + " " + sleep[0] + "," + sleep[1]);
	}

	private void step(GpioPinDigitalOutput gpioStep, long speed) throws InterruptedException {
		int[] sleep = Speed.sleep(speed);
		gpioStep.setState(true);
		Thread.sleep(1);
		gpioStep.setState(false);
		Thread.sleep(sleep[0], sleep[1]);
	}

	public void move(int speed) {
		this.work = speed != 0;
		this.direction = speed >= 0;
		this.speed = Math.abs(speed);
	}

}
