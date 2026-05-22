package pl.kinga.kolekcjegeneryki.ecommerce;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProductCatalog {
    private Set<Product> products;

    public ProductCatalog() {
        this.products = new HashSet<>();
    }

    public String addProduct(Product product) {
        boolean addedValue = products.add(product);
        if (addedValue) {
            return "The product is added";
        } else {
            return "This product already exists we cannot add it!";
        }
    }

    public void removeProduct(String sku) {
        products.removeIf(p -> p.getSku().equals(sku));
    }

    public boolean hasProduct(String sku) {
        return products.stream().anyMatch(p -> p.getSku().equals(sku));
    }

    public int getProductCount() {
        return products.size();
    }

    public Set<Product> getAllProducts() {
        return Collections.unmodifiableSet(products);
    }


}
