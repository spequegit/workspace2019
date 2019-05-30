package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class streamTest {

	
	public static void main(String[] args) {
		
//		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//		System.out.println(converter.convert("1233"));
		
		
//		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//		
//		names.sort((a,b) -> a.compareTo(b));
//		
//		
//		
//		System.out.println(names);
//		
//		
//		Optional<String> findFirst = names.stream().findFirst();
//	
//		
//		System.out.println(findFirst.get());
//		
//		
//		System.out.println(converter.dupa());
//		System.out.println(new ExtraConverter().dupa());
		
		
		StatInt si = new StatCls()::robcos;
//		Class2 c2 = StatCls::robcos;
		System.out.println(si.statMet("3","dupa"));
		
		
//		Person p = Person::new;
	}
}

@FunctionalInterface
interface Converter<F,T>{
	T convert(F from);
	
	default int dupa() {
		return 3;
	}
}

class Person {
    String firstName;
    String lastName;
 
    Person() {}
 
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

//class Class2 implements StatInt{
//
//	@Override
//	public Object statMet() {
//		System.out.println("statmetimpl");
//		return 2;
//	}
//	
//}

@FunctionalInterface
interface StatInt<X,Y,Z>{
	X statMet(Y y,Z z);
}

class StatCls{
	public int robcos(Object i, Object s) {
		System.out.println(s+"robcos "+i);
		return 11;
	}
}

class ExtraConverter implements Converter{

	@Override
	public Object convert(Object from) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void statyczna() {
		
	}
	

	
}