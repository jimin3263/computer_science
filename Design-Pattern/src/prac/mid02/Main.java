package prac.mid02;

import prac.mid02.search.BinarySearch;

public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9,24,2,4,76,2};

        Array arr1 = new Array(new BinarySearch());
        System.out.println(arr1.search(arr,10));
    }
}
