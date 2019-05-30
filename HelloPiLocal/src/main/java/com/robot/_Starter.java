package com.robot;

import java.io.IOException;
import java.net.UnknownHostException;

import com.robot.client.RobotClientController;

public class _Starter {

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {

		new Thread() {
			@Override
			public void run() {
				try {
					new RobotClientController("localhost");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new RobotServer();
	}
}
