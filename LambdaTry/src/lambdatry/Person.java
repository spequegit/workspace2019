package lambdatry;

public class Person {
	private String name;
	private int age;

	public Person(String string, int i) {
		name = string;
		age = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String givenName) {
		this.name = givenName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void print() {
		System.out.println(name + " " + age);
	}

}
