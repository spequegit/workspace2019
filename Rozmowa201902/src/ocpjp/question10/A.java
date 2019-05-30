package ocpjp.question10;

public interface A {
//	public void doSomething(String s)	compile
}

class AImpl implements A{

//	@Override
//	public void doSomething(String s) {
//	}
	
}

class B{
	public A doSomething() {
		return null;
	}
	
	public String exceute() {
		return null;
	}
}

class C extends B{
	@Override
	public AImpl doSomething() {
		return null;
	}
	
	public Object execute() {
		return null;
	}
}