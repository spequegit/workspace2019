package archive02;

public class Program1 {

	private SensorController controller;
	private MovementController movementController;

	public void start() throws InterruptedException {
		controller = new SensorController();
		controller.start();
		movementController = new MovementController();

		while (true) {
			movementController.forward();
			Thread.sleep(1000);
			if (controller.closerThan(50)) {
				movementController.stop();
			}
		}
	}
}
