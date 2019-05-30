package com.robot.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class RobotClientKeyListener implements KeyListener {

	private Socket client;
	private Set<Integer> keySet = new HashSet<>();

	public RobotClientKeyListener(Socket client) {
		this.client = client;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keySet.remove(e.getKeyCode());
		send(keySet);
	};

	@Override
	public void keyPressed(KeyEvent e) {
		keySet.add(e.getKeyCode());
		send(keySet);
		if (e.getKeyCode() == KeyEvent.VK_X) {
			System.exit(0);
		}
	}

	private void send(Set<Integer> keySet) {
		try {
			OutputStream outputStream = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(keySet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
