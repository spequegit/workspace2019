import java.util.concurrent.CopyOnWriteArrayList;

public class Example {
	public static void main(String[] args) {
		Integer a = 12____8;
		int b = 1_____2_8; // _ only a separator JAVA7

		System.out.println(a.equals(b)); // true
//		System.out.println(a == 127); // true
//		System.out.println(new Integer(127).equals(b)); // true
		System.out.println(a == b); // true
		System.out.println(a == (Integer) b); // true
		System.out.println(new Integer(a) == new Integer(b)); // false
		System.out.println(new Integer(a).equals(new Integer(b))); // true

		new Beta();
		
		
		CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();	//	JAVA 5

	}
}

class Alpha {

	Alpha() {
		System.out.println("construct A");	//	constructor at the end / parent first
	}

	{
		System.out.println("default A");	// default like constructor
	}

	static {
		System.out.println("static A");		// static blocks at first / parent first
	}
}

class Beta extends Alpha {

	Beta() {
		System.out.println("construct B");	//	constructor at the end / child second
	}

	{
		System.out.println("default B");	// default like constructor
	}

	static {
		System.out.println("static B");		// static blocks at first / child second
	}

}

class Gamma {
	static {
		System.out.println("static C");		// not used
	}
}
