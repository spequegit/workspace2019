import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SomeComponent {
	
	@Autowired
	private SomeComponent someComponent;

	public SomeComponent() {
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
		System.out.println("Some--------------------------------------------------------------------");
	}
	
}
