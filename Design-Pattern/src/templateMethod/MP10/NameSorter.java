package templateMethod.MP10;

public class NameSorter extends BubbleSorter {

    @Override
    public int compareTo(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}
