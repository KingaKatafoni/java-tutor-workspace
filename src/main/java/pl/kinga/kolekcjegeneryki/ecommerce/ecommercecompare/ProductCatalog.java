package pl.kinga.kolekcjegeneryki.ecommerce.ecommercecompare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void sortByPriceAscending() {
        products.sort(Comparator.comparing(Product::price));
    }

    public void sortByPriceDescending() {
        products.sort(Comparator.comparing(Product::price).reversed());
    }

    public void sortByRatingDescending() {
        products.sort(Comparator.comparing(Product::rating).reversed());
    }

    public void sortByNameThenPrice() {
        products.sort(Comparator.comparing(Product::name).thenComparing(Product::price));
    }

    public void printAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

}
