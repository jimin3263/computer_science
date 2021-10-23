package iterator.iterator;

import java.util.Iterator;

public class IntDynamicArrayIterator implements Iterator {
    IntDynamicArray arr;
    int count; //현재 어딨는지

    //생성자 -> 배열 가져오기
    public IntDynamicArrayIterator(IntDynamicArray arr) {
        this.arr = arr;
        count = 0;
    }

    //hasnext, next 구현
    @Override
    public boolean hasNext() {
        return count < arr.getSize();
    }

    @Override
    public Object next() {
        Integer n = arr.get(count);
        count++;
        return n;
    }

}
