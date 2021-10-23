package prac.mid02.search;

public class BinarySearch implements SearchList {
    @Override
    public boolean search(int[] arr, int key) {
        int mid;
        int low = 0;
        int high = arr.length;
        while(low <= high) {
            mid = (low + high) / 2;
            if(key == arr[mid]) {
                return true;
            } else if(key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false; // 탐색 실패
    }

}
