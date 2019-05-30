package archive01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class StepClient {

	private static final String HOST = "192.168.0.133";
	// private static final String HOST = "localhost";

	public StepClient() throws UnknownHostException, IOException {
		Socket client = new Socket(HOST, 10);
		OutputStream outputStream = client.getOutputStream();

		JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(200, 200);
		jFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					System.out.println("stop");
					outputStream.write(KeyEvent.VK_S);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println(keyCode);
				try {
					outputStream.write(keyCode);
					if (keyCode == KeyEvent.VK_X) {
						client.close();
						System.exit(0);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new StepClient();
	}

};
