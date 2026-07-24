package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_7;

import java.util.ArrayList;
import java.util.List;

public class InvoiceObserver implements OrderObserver {
    private final List<String> generatedInvoices = new ArrayList<>();


    @Override
    public void onOrderPlaced(Order order) {
        generatedInvoices.add("Invoice for " + order.orderId() + ": " + order.totalPrice() + " PLN");
    }

    public List<String> getGeneratedInvoices() {
        return generatedInvoices;
    }
}
