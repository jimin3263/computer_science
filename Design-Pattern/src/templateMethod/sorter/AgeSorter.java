package templateMethod.sorter;

public class AgeSorter extends Sorter{

    @Override
    public int compareTo(Person person1, Person person2) {
        return person1.getAge()- person2.getAge();
    }
}
