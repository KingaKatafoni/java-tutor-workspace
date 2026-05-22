package pl.kinga.kolekcjegeneryki.ecommerce;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String sku;
    private final String name;
    private final BigDecimal price;

    public Product(String sku, String name, BigDecimal price) {
        if (sku == null || sku.isEmpty() || name == null || name.isEmpty() || price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product other = (Product) o;
        return sku.equals(other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
