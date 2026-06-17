package pl.kinga.funkcyjnajava.lekcja5_1.ecommerce;

import java.math.BigDecimal;

public record Order(String id, String customerName, BigDecimal amount, String status, String category) {

}
