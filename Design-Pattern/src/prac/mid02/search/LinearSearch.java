package prac.mid02.search;

public class LinearSearch implements SearchList{
    @Override
    public boolean search(int[] arr, int key) {
        int i = 0;
        while (true) { //시간복잡도 O(n/2)
            if (i == key)
                return false;		// 검색 실패!(-1을 반환)
            if (arr[i] == key)
                return true;		// 검색 성공!(인덱스를 반환)
            i++;
        }
    }

}
