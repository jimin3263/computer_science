package adapter.MP07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataCollectionAdapter<T> implements DataCollection<T>{
    List<T> list;

    public DataCollectionAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean put(T t) {
        return list.add(t);
    }

    @Override
    public T elemAt(int index) {
        return list.get(index);
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Iterator createIterator() {
        return list.iterator();
    }

}

