package templateMethod.MP10;

public class AgeSorter extends BubbleSorter{
    public void bubbleSort(Person[] data){
        sort(data);
    }

    @Override
    public int compareTo(Person a, Person b) {
        return a.getAge() - b.getAge();
    }
}
