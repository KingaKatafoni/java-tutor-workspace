package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_7;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        if (!observers.contains(observer)) {
            throw new IllegalArgumentException("Observer does not exist");
        }

        observers.remove(observer);
    }

    public int getObserversCount() {
        return observers.size();
    }

    public String placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        notifyObservers(order);

        return "Order placed: " + order.orderId();
    }

    private void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(order);
        }
    }


}
