package archive02;

import java.net.Socket;

public class RobotClient {

	private static final String HOST = "192.168.0.133";
	// private static final String HOST = "localhost";
	private RobotClientView view = new RobotClientView();

	public RobotClient() throws InterruptedException {
		while (true) {
			System.out.print("connecting... ");
			try {
				Socket client = new Socket(HOST, RobotServer.PORT);
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

	public static void main(String[] args) throws InterruptedException {
		new RobotClient();
	}
};
