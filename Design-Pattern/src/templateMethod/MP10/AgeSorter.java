package templateMethod.MP10;

public class AgeSorter extends BubbleSorter{

    @Override
    public int compareTo(Person a, Person b) {
        return a.getAge() - b.getAge();
    }
}
