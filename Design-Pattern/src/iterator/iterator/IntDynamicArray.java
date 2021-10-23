package iterator.iterator;

import java.util.Iterator;

public class IntDynamicArray {
    final int INCREMENT_SIZE = 10;
    int count; //현재 인덱스
    int size; //전체크기
    private int[] arr;

    public IntDynamicArray(){
        count =0;
        arr = new int[INCREMENT_SIZE];
        size = INCREMENT_SIZE;
    }

    public void add(int n){
        if (count >= size) { //못들어가는 곳, 원래 있던 곳에서 공간 늘림
            int[] arr2 = new int[size + INCREMENT_SIZE];
            for (int i = 0; i < size; i++) {
                arr2[i] = arr[i];
            }
            size += INCREMENT_SIZE;
            arr = arr2;
        }
        arr[count] = n;
        count++;
    }

    public int get(int idx) throws ArrayIndexOutOfBoundsException {
        if(idx < size){
            return arr[idx];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getSize(){
        return count;
    }

    public Iterator iterator(){
        return new IntDynamicArrayIterator(this);
    }
}
