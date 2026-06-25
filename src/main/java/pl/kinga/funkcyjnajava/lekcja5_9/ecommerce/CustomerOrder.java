package pl.kinga.funkcyjnajava.lekcja5_9.ecommerce;

import java.math.BigDecimal;

public record CustomerOrder(String orderId, String customerName, String category, BigDecimal totalPrice, int itemCount, String status) {
}
