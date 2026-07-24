package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_7;

import java.util.ArrayList;
import java.util.List;

public class EmailObserver implements OrderObserver {
    private final List<String> sentEmails = new ArrayList<>();

    @Override
    public void onOrderPlaced(Order order) {
        sentEmails.add("Email to " + order.customerEmail() + " for order " + order.orderId());
    }

    public List<String> getSentEmails() {
        return sentEmails;
    }
}
