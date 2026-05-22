package pl.kinga.kolekcjegeneryki.ecommerce;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ProductCatalog productCatalog = new ProductCatalog();
        System.out.println(productCatalog.addProduct(new Product("SKU-001", "Laptop", new BigDecimal("1599.99"))));
        System.out.println(productCatalog.addProduct(new Product("SKU-002", "Phone", new BigDecimal("4599.99"))));
        System.out.println(productCatalog.addProduct(new Product("SKU-003", "Mouse", new BigDecimal("99.99"))));
        System.out.println(productCatalog.addProduct(new Product("SKU-004", "Monitor", new BigDecimal("899.99"))));
        System.out.println(productCatalog.addProduct(new Product("SKU-005", "Mouse", new BigDecimal("199.99"))));

        System.out.println(productCatalog.addProduct(new Product("SKU-003", "Monitor", new BigDecimal("399.99"))));

        System.out.println(productCatalog.hasProduct("SKU-001"));
        System.out.println("Amount of products before remove(): " + productCatalog.getProductCount());
        productCatalog.removeProduct("SKU-003");
        System.out.println("Amount of products after remove(): " + productCatalog.getProductCount());
        System.out.println(productCatalog.getAllProducts());
    }
}
