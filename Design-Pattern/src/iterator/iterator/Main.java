package iterator.iterator;

import java.util.Iterator;

//객체의 내부 구조에 대한 이해 없이 각 요소를 순서대로 접근
public class Main {
    public static void main(String[] args) {
        IntDynamicArray arr = new IntDynamicArray();
        for (int i = 0; i < 15; i++) {
            arr.add(i);
        }

        System.out.println("==for + get()==");
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }

        System.out.println("==for+iterator==");
        for (Iterator itr = arr.iterator(); itr.hasNext();){
            System.out.println(itr.next());
        }

        /*
        //arr implements iterable 해야 함
        for (Object o : arr) {
            System.out.println((Integer) o;
            
        }

         */
    }

}
