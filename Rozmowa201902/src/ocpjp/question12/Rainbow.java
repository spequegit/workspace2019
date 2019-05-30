package ocpjp.question12;

public class Rainbow {

	public enum MyColor{
		RED(10), GREEN(20), BLUE(30);
		private final int c;
		private MyColor(int c) {
			this.c=c;
		}
		public int getColor() {
			return c;
		}
	}
	
	public static void main(String[] args) {
		MyColor treeColor = MyColor.GREEN;
		
		System.out.println(treeColor);
	}
}
