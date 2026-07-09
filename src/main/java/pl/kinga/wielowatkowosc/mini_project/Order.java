package pl.kinga.wielowatkowosc.mini_project;

import java.math.BigDecimal;

public class Order {
    private final String orderId;
    private final String customerName;
    private final String product;
    private final BigDecimal amount;

    public Order(String orderId, String customerName, String product, BigDecimal amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return orderId + ": " + product + " (" + amount + " PLN)";
    }
}
