
public class IntegerPoolExample {

	
	public static void main(String[] args) {
		
		// in integer pool range
		checkEquality(127, 127);		//true
		// out of integer pool range
		checkEquality(-128, -128);	//true
		checkEquality(-129, -129);	//false
		checkEquality(128, 128);		//false
		
		for(int i = -130; i < 130; i++) {
			System.out.print(i+" ");
			checkEquality(i, i);
		}
	}

	private static void checkEquality(Integer a, Integer b) {
		System.out.println(a == b);
//		System.out.println(a.equals(b));
//		System.out.println(new Integer(a) == new Integer(b));
//		System.out.println(new Integer(a).equals(new Integer(b)));
//		System.out.println("------------------------------------------------");
	}
}
