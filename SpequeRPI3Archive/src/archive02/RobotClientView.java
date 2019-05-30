package archive02;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class RobotClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSlider maxSpeed;

	public RobotClientView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		// setLocationRelativeTo(null);
		// setLayout(new FlowLayout());
		// maxSpeed = new JSlider(0, 100);
		// maxSpeed.setPaintTicks(true);
		// maxSpeed.setPaintTrack(true);
		// maxSpeed.setPaintLabels(true);
		// maxSpeed.setPreferredSize(new Dimension(300, 50));
		// add(maxSpeed);

		setVisible(true);
	}

	public JSlider getMaxSpeed() {
		return maxSpeed;
	}

};
