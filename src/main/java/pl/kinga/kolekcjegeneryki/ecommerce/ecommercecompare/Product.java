package pl.kinga.kolekcjegeneryki.ecommerce.ecommercecompare;

import java.math.BigDecimal;

public record Product(String name, BigDecimal price, double rating) {

    public Product {
        if (name == null || name.isEmpty() ||
                price.compareTo(BigDecimal.ZERO) < 0 || rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

}
