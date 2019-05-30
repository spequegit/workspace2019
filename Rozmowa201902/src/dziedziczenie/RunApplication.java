package dziedziczenie;
import java.util.ArrayList;
import java.util.List;

public class RunApplication {
	public static void main(String[] args) {
		Vehicle v = new Vehicle();
//		Car c = new Vehicle();			// nie stworzysz bo vehicle nie ma juz tego co car
//		Audi audi2 = new Car();			// a car nie ma tego co audi
		Vehicle vc = new Car();
		Vehicle vt = new Truck();
		Car car = new Car();
		Truck t = new Truck();
		Audi audi = new Audi();
		Dziwnochod d = new Dziwnochod();
		
//		v.startEngine();
//		vc.startEngine();
//		vt.startEngine();
//		car.startEngine();
//		t.startEngine();
//		((Audi)car).startEngine();
//		audi.startEngine();
		
		Car carFromAudi = new Audi();
		carFromAudi.startEngine();
		((Audi)carFromAudi).extraMetodaAudi(); // rzutowanie pozwala nam się dostac do metod specyficznych dla dziecka
		((Opel)carFromAudi).extraMetodaOpel(); // audi nie jest oplem = class cast exception
		
		List<Vehicle> list = new ArrayList<>();
		list.add(v);
		list.add(vc);
		list.add(vt);
		list.add(car);
		list.add(t);
		list.add(audi);
		list.add(d);
		
		for (Vehicle vehicle : list) {
//			vehicle.startEngine();
		}
//		list.stream().forEach(x -> x.startEngine());
//		list.stream().count();
		list.stream().limit(3).forEach(x -> x.startEngine());
		
		AbstractVehicle av = new AbstractVehicle() {
			@Override
			public void startEngine() {
				// TODO Auto-generated method stub
			}
		};
	}
}
