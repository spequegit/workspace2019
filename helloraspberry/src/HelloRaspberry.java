import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class HelloRaspberry {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello Raspberry!");

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		// provision gpio pin #01 & #03 as an output pins and blink
		final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);

		while (true) {
			led1.setState(true);
			Thread.sleep(200);
			led1.setState(false);
			Thread.sleep(200);
			System.out.print(".");
		}
	}
}
