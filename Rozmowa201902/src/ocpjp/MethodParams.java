package ocpjp;
public class MethodParams{
	
	public void tryParam3(String[]...s) {
		
	}
	public void tryParam2(int[]...s) {
		
	}
	public void tryParam(int i,String...s) {
		
	}
//	public void tryParam(String... s, int i) {	// parametr tablicowy musi być ostatni
//		
//	}
	
	public static void main(String[] args) {
		new MethodParams().tryParam(5, "assa","sasa","sassasa","sassa","dsd","assasasa");
		new MethodParams().tryParam2(new int[]{3211,21312,21321});
		new MethodParams().tryParam3(new String[]{"assa","assa","assa"},new String[]{"assa","assa","assa"},new String[]{"assa","assa","assa"});
	}
}
