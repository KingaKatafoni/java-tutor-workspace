package pl.kinga.funkcyjnajava.lekcja5_8.ecommerce;

import java.math.BigDecimal;

public record Product(String sku, String name, String category, BigDecimal price, int stockQuantity, boolean active) {
}
