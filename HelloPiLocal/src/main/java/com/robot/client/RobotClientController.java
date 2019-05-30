package com.robot.client;

import java.net.Socket;

import com.robot.RobotServer;

public class RobotClientController {

	private String host = "192.168.43.3";
	private RobotClientView view = new RobotClientView();

	public RobotClientController() throws InterruptedException {
		go();
	}

	private void go() throws InterruptedException {
		while (true) {
			System.out.print("connecting... ");
			try {
				Socket client = new Socket(host, RobotServer.PORT);
				System.out.println("connected");
				view.addKeyListener(new RobotClientKeyListener(client));
				while (true) {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			Thread.sleep(1000);
		}
	}

	public RobotClientController(String host) throws InterruptedException {
		this.host = host;
		go();
	}

	public static void main(String[] args) throws InterruptedException {
		new RobotClientController();
	}
};
