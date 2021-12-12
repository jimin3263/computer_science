package templateMethod.sorter;

public class NameSorter extends Sorter{


    @Override
    public int compareTo(Person person1, Person person2) {
        return person1.getName().compareTo(person2.getName());
    }
}
