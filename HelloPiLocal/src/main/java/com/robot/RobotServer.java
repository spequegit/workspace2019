package com.robot;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

import com.google.common.collect.Sets;
import com.robot.buzzer.Buzzer;
import com.robot.motor.MotorController;
import com.robot.program.Program1;
import com.robot.program.Program2;

public class RobotServer {

	public static final int PORT = 10;
	private Buzzer buzzer = new Buzzer();

	public RobotServer() throws IOException, ClassNotFoundException, InterruptedException {
		Socket socket = connect();
		processOperation(socket);
	}

	@SuppressWarnings("resource")
	private Socket connect() throws IOException {
		System.out.println("waiting...");
		buzzer.horn();
		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket socket = serverSocket.accept();
		System.out.println("standing by...");
		buzzer.horn();
		buzzer.horn();
		return socket;
	}

	public void processOperation(Socket socket) throws IOException, ClassNotFoundException, InterruptedException {
		MotorController controller = new MotorController();
		while (true) {
			Set<Integer> keySet = readKeySet(socket);
			// System.out.println(keySet);

			if (keySet.size() == 0) {
				controller.stop();
			}
			if (keySet.size() == 1) {
				if (keySet.contains(KeyEvent.VK_SHIFT)) {
					controller.stop();
				}
				if (keySet.contains(KeyEvent.VK_1)) {
					new Program1();
				}
				if (keySet.contains(KeyEvent.VK_2)) {
					new Program2(controller).start();
				}
				if (keySet.contains(KeyEvent.VK_X)) {
					exit(socket);
				}
				if (keySet.contains(KeyEvent.VK_UP)) {
					controller.forward();
				}
				if (keySet.contains(KeyEvent.VK_DOWN)) {
					controller.backward();
				}
				if (keySet.contains(KeyEvent.VK_LEFT)) {
					controller.left();
				}
				if (keySet.contains(KeyEvent.VK_RIGHT)) {
					controller.right();
				}
				if (keySet.contains(KeyEvent.VK_A)) {
					controller.faster();
				}
				if (keySet.contains(KeyEvent.VK_Z)) {
					controller.slower();
				}
				if (keySet.contains(KeyEvent.VK_D)) {
					controller.changeDirection();
				}
				if (keySet.contains(KeyEvent.VK_SPACE)) {
					buzzer.horn();
				}
			}
			if (keySet.size() == 2) {
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_UP, KeyEvent.VK_RIGHT))) {
					controller.forwardRight();
				}
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_UP, KeyEvent.VK_LEFT))) {
					controller.forwardLeft();
				}
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT))) {
					controller.backwardRight();
				}
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_DOWN, KeyEvent.VK_LEFT))) {
					controller.backwardLeft();
				}
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_SHIFT, KeyEvent.VK_LEFT))) {
					controller.rightWheel(3);
				}
				if (keySet.containsAll(Sets.newHashSet(KeyEvent.VK_SHIFT, KeyEvent.VK_RIGHT))) {
					controller.leftWheel(3);
				}
			}
		}
	}

	private void exit(Socket server) throws IOException {
		server.close();
		System.out.println("closed");
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	public Set<Integer> readKeySet(Socket accept) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());
		Object readObject = ois.readObject();
		return (Set<Integer>) readObject;
	}

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		new RobotServer();
	}
};
