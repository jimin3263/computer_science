package prac.mid02;

import prac.mid02.search.SearchList;

public class Array {
    private SearchList searchList;

    public Array(SearchList searchList) {
        this.searchList = searchList;
    }

    public void setSearchList(SearchList searchList) {
        this.searchList = searchList;
    }

    public boolean search(int[] arr, int key){
        return searchList.search(arr,key);
    }
}
