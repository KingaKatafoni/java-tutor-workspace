package pl.kinga.kolekcjegeneryki.publicsector.publicsectorgenerics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Registry<T extends Identifiable> {
    private List<T> items;

    public Registry() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public T findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void removeById(String id) {
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.getId().equals(id)) {
                it.remove();
            }
        }
    }

    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    public int getCount() {
        return items.size();
    }

    public void printAll() {
        for (T item : items) {
            System.out.println(item);
        }
    }

}
