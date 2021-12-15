package templateMethod.MP10;

public class MainTest {
	public static void main(String[] args) {
		Person[] people = {
			new Person(3000, "Dooly"),
			new Person(30, "Ddochi"),
			new Person(25, "Michol"),
			new Person(20000, "Douner"),
			new Person(3, "HeeDong")
		};
		System.out.println("\noriginal people");
		for (Person p : people) {
			System.out.println(p);
		}
		
		System.out.println("\nsort by Name");
		BubbleSorter sorter = new NameSorter();
		sorter.sort(people);
		for (Person p : people) {
			System.out.println(p);
		}
		
		System.out.println("\nsort by Age");
		sorter = new AgeSorter();
		sorter.sort(people);
		for (Person p : people) {
			System.out.println(p);
		}
	}

}
