package ocpjp.question11;

public class Person {
	String name = "none";
	
	public Person(String s) {
		name = s;
	}
}

class Employee extends Person{

	public Employee(String e) {
		super("dupa");	// musi byc super
	}
	
}