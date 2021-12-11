package adapter.enumeration;

import java.util.Iterator;
import java.util.Vector;

public class Iterator1 {
    public static void printIterator(Iterator it){
        while (it.hasNext()){
            System.out.println(""+it.next());
        }
    }

    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            v.add(i);
        }
        Iterator it = v.iterator();
        Iterator1.printIterator(it);
    }
}
