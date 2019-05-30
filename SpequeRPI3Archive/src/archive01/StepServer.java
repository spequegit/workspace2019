package archive01;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class StepServer {

	public StepServer() throws UnknownHostException, IOException, InterruptedException {

		ServerSocket server = new ServerSocket(10);
		StepController controller = new StepController();
		System.out.println("start");
		Socket accept = server.accept();
		while (true) {
			System.out.print("received: ");
			InputStream inputStream = accept.getInputStream();
			int keyCode = inputStream.read();
			System.out.println(keyCode);
			if (keyCode == KeyEvent.VK_X) {
				break;
			}
			if (keyCode == KeyEvent.VK_UP) {
				System.out.println("up");
				controller.move(true);
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				System.out.println("down");
				controller.move(false);
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				System.out.println("up");
				controller.move2(true);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				System.out.println("down");
				controller.move2(false);
			}
			if (keyCode == KeyEvent.VK_S) {
				System.out.println("stop");
				controller.stop();
				controller.stop2();
			}
		}
		server.close();
		System.out.println("closed");
		System.exit(0);

	}

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		new StepServer();
	}
};
