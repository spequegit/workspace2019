package com.robotpi;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import com.pi4j.io.gpio.RaspiPin;

/**
 * Hello world!
 *
 */
public class MotorServer {

	int speed = 70;
	private ServerSocket serverSocket;

	private Set<Integer> keySet = new HashSet<>();
	private Set<Integer> keySetOld = new HashSet<>();

	private Motor motorFR = new Motor(1, RaspiPin.GPIO_00);
	private Motor motorRR = new Motor(26, RaspiPin.GPIO_22);
	private Motor motorRL = new Motor(24, RaspiPin.GPIO_27);
	private Motor motorFL = new Motor(23, RaspiPin.GPIO_07);

	Scenarios program = new Scenarios(motorFR, motorRR, motorRL, motorFL, speed);

	public void startServer() throws IOException, InterruptedException, ClassNotFoundException {
		serverSocket = new ServerSocket(90);
		program.stop();
		while (true) {
			Socket socket = serverSocket.accept();
			keySet = (Set<Integer>) new ObjectInputStream(socket.getInputStream()).readObject();
			execute(keySet);
		}
	}

	public void execute(Set<Integer> keySet) throws IOException {
		if (keySet.containsAll(keySetOld) && keySetOld.containsAll(keySet) && !keySet.isEmpty()) {
			System.out.print(".");
			return;
		}
		System.out.println(keySet);

		int slower = speed + (int) ((100 - speed) * 0.8);

		System.out.println(speed + " / " + slower);

		program.setSpeed(speed);

		if (keySet.contains(KeyEvent.VK_LEFT) && keySet.contains(KeyEvent.VK_UP)) {
			motorFR.go(speed);
			motorRR.go(speed);
			motorRL.go(slower);
			motorFL.go(slower);
		} else if (keySet.contains(KeyEvent.VK_RIGHT) && keySet.contains(KeyEvent.VK_UP)) {
			motorFR.go(slower);
			motorRR.go(slower);
			motorRL.go(speed);
			motorFL.go(speed);
		} else if (keySet.contains(KeyEvent.VK_UP)) {
			program.go(speed);
		} else if (keySet.contains(KeyEvent.VK_DOWN)) {
			int reverseSpeed = -(100 - speed);
			program.go(reverseSpeed); // was -(100 - speed)
		} else if (keySet.contains(KeyEvent.VK_LEFT)) { // turn left
			program.turnLeft();
		} else if (keySet.contains(KeyEvent.VK_RIGHT)) { // turn right
			program.turnRight();
		}
		// tank
		else if (keySet.contains(KeyEvent.VK_Z) && keySet.contains(KeyEvent.VK_C)) {
			program.go(speed);
		} else if (keySet.contains(KeyEvent.VK_Z)) {
			program.right(speed);
		} else if (keySet.contains(KeyEvent.VK_C)) {
			program.left(speed);
		}

		else if (keySet.contains(KeyEvent.VK_T)) {
			try {
//				program.testRobot();
				program.drawRectangle();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		else if (keySet.contains(KeyEvent.VK_SPACE) || keySet.isEmpty()) {
			program.stop();
		}

		// speed
		if (keySet.contains(KeyEvent.VK_9)) {
			speed = 1;
		} else if (keySet.contains(KeyEvent.VK_8)) {
			speed = 5;
		} else if (keySet.contains(KeyEvent.VK_7)) {
			speed = 10;
		} else if (keySet.contains(KeyEvent.VK_6)) {
			speed = 20;
		} else if (keySet.contains(KeyEvent.VK_5)) {
			speed = 30;
		} else if (keySet.contains(KeyEvent.VK_4)) {
			speed = 40;
		} else if (keySet.contains(KeyEvent.VK_3)) {
			speed = 50;
		} else if (keySet.contains(KeyEvent.VK_2)) {
			speed = 60;
		} else if (keySet.contains(KeyEvent.VK_1)) {
			speed = 70;
		} else if (keySet.contains(KeyEvent.VK_0)) {
			speed = 80;
		} else if (keySet.contains(KeyEvent.VK_X)) {
			System.exit(0);
		}

		keySetOld = keySet;
	}

	public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
		new MotorServer().startServer();
	}
}