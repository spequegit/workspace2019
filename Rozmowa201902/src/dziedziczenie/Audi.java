package dziedziczenie;

public class Audi extends Car {
	@Override
	public void startEngine() {
		System.out.println("audi brum brum");
	}
	
	
	public void extraMetodaAudi() {
		System.out.println("extraMetodaAudi");
	}
	
}
