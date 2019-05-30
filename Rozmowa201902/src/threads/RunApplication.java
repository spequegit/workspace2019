package threads;

public class RunApplication {

	public static void main(String[] args) {
		
		
		MyThreadOne myThreadOne = new MyThreadOne();
		myThreadOne.start();
		myThreadOne.start();		// Exception in thread "main" .java.lang.IllegalThreadStateException
		
		new Thread(new MyThreadTwo()).start();
		
		while(true) {
			System.out.print("-");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThreadOne extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThreadTwo implements Runnable{
	@Override
	public void run() {
		while(true) {
			System.out.print("/");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}