package archive02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class RobotServer {

	public static final int PORT = 10;

	public RobotServer() throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		processOperation(new ServerSocket(PORT));
	}

	public void processOperation(ServerSocket server) throws IOException, ClassNotFoundException, InterruptedException {
		System.out.println("waiting...");
		Socket socket = server.accept();
		System.out.println("standing by...");
		MovementController controller = new MovementController();
		while (true) {
			Operation operation = readOperation(socket);
			System.out.println(operation.getType());

			switch (operation.getType()) {
			case PROGRAM_1:
				new Program1().start();
				break;
			case END:
				exit(server);
				break;
			case STOP:
				controller.stop();
				break;
			case FORWARD:
				controller.forward();
				break;
			case FORWARD_LEFT:
				controller.forwardLeft();
				break;
			case FORWARD_RIGHT:
				controller.forwardRight();
				break;
			case BACKWARD:
				controller.backward();
				break;
			case BACKWARD_LEFT:
				controller.backwardLeft();
				break;
			case BACKWARD_RIGHT:
				controller.backwardRight();
				break;
			case LEFT:
				controller.left();
				break;
			case RIGHT:
				controller.right();
				break;
			case SLOWER:
				controller.slower();
				break;
			case FASTER:
				controller.faster();
				break;
			default:
				break;
			}
		}
	}

	private void exit(ServerSocket server) throws IOException {
		server.close();
		System.out.println("closed");
		System.exit(0);
	}

	public Operation readOperation(Socket accept) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());
		Operation operation = (Operation) ois.readObject();
		return operation;
	}

	// private void search(int keyCode) throws InterruptedException {
	// if (keyCode == KeyEvent.VK_1) {
	// motor1.move(5);
	// Thread.sleep(1000);
	// motor1.move(0);
	// }
	// if (keyCode == KeyEvent.VK_2) {
	// motor2.move(5);
	// Thread.sleep(1000);
	// motor2.move(0);
	// }
	// if (keyCode == KeyEvent.VK_3) {
	// motor3.move(5);
	// Thread.sleep(1000);
	// motor3.move(0);
	// }
	// if (keyCode == KeyEvent.VK_4) {
	// motor4.move(5);
	// Thread.sleep(1000);
	// motor4.move(0);
	// }
	// }

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		new RobotServer();
	}
};
