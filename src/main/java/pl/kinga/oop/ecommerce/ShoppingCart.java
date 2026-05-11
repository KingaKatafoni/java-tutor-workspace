package pl.kinga.oop.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + getItemCount() +
                String.format(", total= %.2f" ,calculateTotal()) +
                '}';
    }
}
