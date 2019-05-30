package archive02;

public class MovementController {

	private int speed = 10;
	private int slower = 10;

	private void move(int frontRight, int rearRight, int frontLeft, int rearLeft) {
		try {
			MotorFactory factory = MotorFactory.getInstance();
			factory.getMotorFR().move(frontRight);
			factory.getMotorRR().move(rearRight);
			factory.getMotorFL().move(frontLeft);
			factory.getMotorRL().move(rearLeft);
		} catch (Error | InterruptedException e) {
			System.err.println("no raspberry pi");
		}
	}

	public void stop() {
		move(0, 0, 0, 0); // FR RR FL RL
	}

	public void forward() {
		move(speed, speed, speed, speed); // FR RR FL RL
	}

	public void backward() {
		move(-speed, -speed, -speed, -speed); // FR RR FL RL
	}

	public void left() {
		move(speed, speed, -speed, -speed); // FR RR FL RL
	}

	public void right() {
		move(-speed, -speed, speed, speed);// FR RR FL RL
	}

	public void forwardLeft() {
		move(speedFaster(), speedFaster(), speedSlower(), speedSlower()); // FR
																			// RR
																			// FL
																			// RL
	}

	private int speedSlower() {
		return slower;
	}

	private int speedFaster() {
		return speed;
	}

	public void forwardRight() {
		move(speedSlower(), speedSlower(), speedFaster(), speedFaster()); // FR
																			// RR
																			// FL
																			// RL
	}

	public void backwardLeft() {
		move(-speed, -speed, -speed + slower, -speed + slower); // FR
																// RR
																// FL
																// RL
	}

	public void backwardRight() {
		move(-speed + slower, -speed + slower, -speed, -speed); // FR RR FL
																// RL
	}

	public void slower() {
		this.speed--;
		System.out.println(speed);
	}

	public void faster() {
		this.speed++;
		System.out.println(speed);
	}

}
