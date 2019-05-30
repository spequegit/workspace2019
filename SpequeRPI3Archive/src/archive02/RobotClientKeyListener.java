package archive02;

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
		operation();
	};

	@Override
	public void keyPressed(KeyEvent e) {
		keySet.add(e.getKeyCode());
		operation();
	}

	private void operation() {
		try {
			if (keySet.contains(KeyEvent.VK_1)) {
				Operation o = new Operation(OperationType.PROGRAM_1);
				send(o);
			} else if (keySet.contains(KeyEvent.VK_S)) {
				Operation o = new Operation(OperationType.SETUP);
				o.setMaxSpeed(30);
				send(o);
			} else if (keySet.contains(KeyEvent.VK_X)) {
				send(new Operation(OperationType.END));
				client.close();
				System.exit(0);
			} else if (keySet.isEmpty()) {
				send(new Operation(OperationType.STOP));
			} else {
				OperationType type = OperationType.findByKeySet(keySet);
				if (type != null) {
					send(new Operation(type));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void send(Operation o) throws IOException {
		if (o != null) {
			OutputStream outputStream = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(o);
		}
	}
}
