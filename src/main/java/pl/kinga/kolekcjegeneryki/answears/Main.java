package pl.kinga.kolekcjegeneryki.answears;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct(new Product("Laptop", "Electronics", new BigDecimal("3499.99"), 4.5, true));
        catalog.addProduct(new Product("Java in Action", "Books", new BigDecimal("89.90"), 4.8, true));
        catalog.addProduct(new Product("iPhone", "Electronics", new BigDecimal("899.99"), 4.0, true));
        catalog.addProduct(new Product("Clean Code", "Books", new BigDecimal("109.90"), 4.9, true));
        catalog.addProduct(new Product("Versace dress", "Clothing", new BigDecimal("13499.99"), 4.1, false));
        catalog.addProduct(new Product("Pillow", "Home", new BigDecimal("9.90"), 3.9, true));
        catalog.addProduct(new Product("Flower pot", "Home", new BigDecimal("99.99"), 4.0, true));
        catalog.addProduct(new Product("Bible", "Books", new BigDecimal("189.90"), 5.0, true));
        catalog.addProduct(new Product("Monitor", "Electronics", new BigDecimal("499.99"), 4.3, true));
        catalog.addProduct(new Product("Jacket", "Clothing", new BigDecimal("59.90"), 1.2, false));

        System.out.println("-----------All products----------");
        catalog.printAll();

        System.out.println("-----------Sorted By price----------");
        catalog.sortByPrice();
        catalog.printAll();

        System.out.println("-----------Sorted by rating descending----------");
        catalog.sortByRatingDescending();
        catalog.printAll();

        System.out.println("-----------Filter by category----------");
        Map<String, List<Product>> grouped = catalog.groupByCategory();
        System.out.println("Electronics: " + grouped.get("Electronics"));

        System.out.println("-----------Filter by price range----------");
        System.out.println(catalog.getInPriceRange(new BigDecimal("50.00"), new BigDecimal("200.00")));

        System.out.println("-----------Group by category----------");
        for (Map.Entry<String, List<Product>> entry : catalog.groupByCategory().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("-----------Unique category----------");
        System.out.println(catalog.getUniqueCategories());

        System.out.println("-----------Min and Max----------");
        System.out.println("Cheapest product: " + catalog.getCheapest());
        System.out.println("Most expensive product: " + catalog.getMostExpensive());

        System.out.println("-----------Remove out of stock----------");
        catalog.removeOutOfStock();
        catalog.printAll();

        System.out.println("-----------Remove cheaper than----------");
        catalog.removeCheaperThan(new BigDecimal("50.00"));
        catalog.printAll();

        System.out.println("-----------Get Products Read Only----------");
        //catalog.getProductsReadOnly().add(new Product("Pain killer", "Medicine", new BigDecimal("9.99"), 4.6, true));//UnsupportedOperationException


    }
}
