package pl.kinga.kolekcjegeneryki.logistics.logisticsgeneric;

import java.util.ArrayList;
import java.util.List;

public class Warehouse<T> {
    private List<T> items;

    public Warehouse() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public T getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void printAll() {
        for (T item : items) {
            System.out.println(item);
        }
    }


}
