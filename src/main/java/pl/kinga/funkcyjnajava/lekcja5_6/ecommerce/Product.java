package pl.kinga.funkcyjnajava.lekcja5_6.ecommerce;

import java.math.BigDecimal;

public record Product(String sku, String name, String category, BigDecimal price, int stock, double rating) {
}
