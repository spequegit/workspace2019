package archive02;
public class MotorFactory {
	private final Motor motor1 = new Motor(RaspiPin.GPIO_22, RaspiPin.GPIO_08);
	private final Motor motor2 = new Motor(RaspiPin.GPIO_23, RaspiPin.GPIO_09);
	private final Motor motor3 = new Motor(RaspiPin.GPIO_24, RaspiPin.GPIO_07);
	private final Motor motor4 = new Motor(RaspiPin.GPIO_25, RaspiPin.GPIO_00);
	private final Motor motorFL = motor1;
	private final Motor motorRL = motor3;
	private final Motor motorFR = motor2;
	private final Motor motorRR = motor4;

	private static MotorFactory instance = null;

	private MotorFactory() throws InterruptedException {

	}

	public static MotorFactory getInstance() throws InterruptedException {
		if (instance == null) {
			instance = new MotorFactory();
			return instance;
		} else {
			return instance;
		}
	}

	public Motor getMotorFR() {
		return motorFR;
	}

	public Motor getMotorRR() {
		return motorRR;
	}

	public Motor getMotorFL() {
		return motorFL;
	}

	public Motor getMotorRL() {
		return motorRL;
	}
}
