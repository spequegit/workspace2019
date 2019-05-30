package ocpjp;

//import ocpjp.Visibility.color;	// lub bez...

public class Visibility {
	
	public enum color {RED, BLUE, YELLOW};
	
	public static void main(String[] args) {
		new User();
	}

}

class User{
	
	public User() {
//		System.out.println(color.RED);	// to lub...
		System.out.println(Visibility.color.RED);
	}
}
