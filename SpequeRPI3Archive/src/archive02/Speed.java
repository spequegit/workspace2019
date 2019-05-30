package archive02;

public class Speed {

	private static final int MAX = 100;
	private static final int MILI_AS_NANOS = 1000000;

	public static int[] sleep(long speed) {
		double i = (double) speed / MAX;
		double nanos = (1 / i);
		double mod = nanos % 1;
		double jed = nanos - mod;
		int[] result = { (int) jed, (int) (mod * MILI_AS_NANOS) };
		return result;
	}
}
