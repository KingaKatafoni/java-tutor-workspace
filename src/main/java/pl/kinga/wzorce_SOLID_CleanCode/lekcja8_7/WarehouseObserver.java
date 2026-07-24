package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_7;

import java.util.ArrayList;
import java.util.List;

public class WarehouseObserver implements OrderObserver {
    private final List<String> preparedOrders = new ArrayList<>();


    @Override
    public void onOrderPlaced(Order order) {
        preparedOrders.add("Prepare package: " + order.orderId());
    }

    public List<String> getPreparedOrders() {
        return preparedOrders;
    }
}
