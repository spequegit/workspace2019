package archive01;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Led {

	private final Pin red = RaspiPin.GPIO_00;
	private final Pin blue = RaspiPin.GPIO_06;
	private final Pin yellow = RaspiPin.GPIO_21;
	private final Pin green = RaspiPin.GPIO_24;

	public Led(String[] arg) {
		GpioController controller = GpioFactory.getInstance();
		Map<String, Pin> map = new HashMap<String, Pin>();
		map.put("r", red);
		map.put("g", green);
		map.put("b", blue);
		map.put("y", yellow);

		for (String string : arg) {
			Pin pin = map.get(string);
			GpioPinDigitalOutput led = controller.provisionDigitalOutputPin(pin);
			led.setState(led.isLow());
			System.out.println(led.getName() + ":" + led.isHigh());
		}
	}

	public static void main(String[] args) {
		new Led(args);
	}
}
