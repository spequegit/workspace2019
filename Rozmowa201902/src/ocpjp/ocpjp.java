package ocpjp;

public class ocpjp {

	
	public static void main(String[] args) {
        int $ = 23;
        System.out.println($);
        
        
        String s1 = "Java";
        String s2 = "Java";
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Ja").append("va");
        
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(sb1.toString() == s1);
        System.out.println(sb1.toString().equals(s1));
        System.out.println("dupa" == "dupa");
	}
	
	
	public Object execute() {
		return null;
	}
}
