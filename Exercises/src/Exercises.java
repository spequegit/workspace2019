import java.util.ArrayList;
import java.util.List;

public class Exercises {
	
	public Exercises() {
		System.out.println(doFactorial(10));
		System.out.println(doFactorialR(10));
		
		List<Integer> list = new ArrayList<>();
		
		list.add(4);
		list.add(2);
		list.add(1);
		list.add(3);
		
		System.out.println(doBubbleSort(list));
		
	}
	
	private int doFactorial(int in) {
		int result = 1;
		for (int i = 1; i <= in; i++) {
			result = i * result;
		}
		return result;
	}
	
	private int doFactorialR(int i) {
		return i>1?doFactorialR(i-1)*i:1;
	}
	
	
	private Iterable<Integer> doBubbleSort(List<Integer> list){
		System.out.println(list);
		List<Integer> result = new ArrayList<>(list);
		for (int i = 0; i < list.size(); i++) {
			if(list.size()>i+1) {
				if(list.get(i)>list.get(i+1)) {
					result.set(i, list.get(i+1));
					result.set(i+1, list.get(i));
				} else {
					result.set(i, list.get(i));
					result.set(i+1, list.get(i+1));
				}
			}
			System.out.println(result);
		}
		return result;
	}
	
	public static void main(String[] args) {
		new Exercises();
	}

}
