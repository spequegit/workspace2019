package lambdatry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JButton;

public class LambdaTry {

	public LambdaTry() {

//		example1();
//		exampleCompare();
//		exampleActionListener();
//		examplePredicate();

	}

	private void examplePredicate() {
		List<Person> people = new ArrayList<>();

		people.add(new Person("fg", 6));
		people.add(new Person("fh", 9));
		people.add(new Person("hj", 36));

		Predicate<Person> containH = p -> p.getName().contains("h");
		Predicate<Person> olderThan10 = p -> p.getAge() > 10;

		Predicate<Person> ageRange = p -> p.getAge() > 40 && p.getAge() < 50;

		if (containH.test(new Person("yesWeHave", 34))) {
			System.out.println("contain H");
		}

		if (olderThan10.test(new Person("yesWeHave", 34))) {
			System.out.println("older than 10");
		}

		if (ageRange.negate().test(new Person("JJ", 55))) {
			System.out.println("???");
		}
	}

	private void exampleActionListener() {
		new JButton().addActionListener(e -> System.out.println("klikniety"));
	}

	private void exampleCompare() {
		List<Person> personList = new ArrayList<>();

		personList.add(new Person("jan", 6));
		personList.add(new Person("zuza", 9));
		personList.add(new Person("dagmara", 36));

		Collections.sort(personList, new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			};
		});

		for (Person p : personList) {
			p.print();
		}

		//

		Collections.sort(personList, (p1, p2) -> p1.getName().compareTo(p2.getName()));
		for (Person p : personList) {
			p.print();
		}
	}

	private void example1() {
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("h1");
			}
		};

		r1.run();

		Runnable r2 = () -> System.out.println("h2");
		r2.run();
	}

	public static void main(String[] args) {
		new LambdaTry();
	}
}
