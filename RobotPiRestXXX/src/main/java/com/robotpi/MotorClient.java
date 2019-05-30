package com.robotpi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class MotorClient {

	private JFrame jFrame = new JFrame();

	private Set<Integer> keySet = new HashSet<>();

	public MotorClient() throws UnknownHostException, IOException {
		initGui();
	}

	private void initGui() {
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keySet.remove(e.getKeyCode());
				keySet.remove(18); // ???
				move();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				keySet.add(e.getKeyCode());
				move();
			}
		});
	}

	private void move() {
		System.out.println(keySet);
		try {
			Socket socket = new Socket("raspberrypi", 90);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(keySet);
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("connection error 1");
		} catch (IOException e) {
			System.err.println("connection error 2");
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new MotorClient();
	}
}
