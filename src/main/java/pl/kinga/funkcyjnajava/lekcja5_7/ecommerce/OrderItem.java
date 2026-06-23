package pl.kinga.funkcyjnajava.lekcja5_7.ecommerce;

import java.math.BigDecimal;

public record OrderItem(String orderId, String productName, int quantity, BigDecimal unitPrice, String status) {
}
