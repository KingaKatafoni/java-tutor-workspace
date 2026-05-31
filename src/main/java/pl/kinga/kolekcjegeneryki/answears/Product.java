package pl.kinga.kolekcjegeneryki.answears;

import java.math.BigDecimal;

public record Product(String name, String category, BigDecimal price, double rating, boolean inStock) {
    public Product {
        if (name == null || name.isEmpty() ||
                category == null || category.isEmpty() ||
                price == null || price.compareTo(BigDecimal.ZERO) <= 0 ||
                rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Input value!");
        }
    }
}
