package pl.kinga.funkcyjnajava.lekcja5_6.ecommerce;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ProductCatalog {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("SKU/001", "Clean Code", "BOOK", new BigDecimal("39.99"), 2, 4.9),
                new Product("SKU/002", "Console", "ELECTRONICS", new BigDecimal("1239.99"), 0, 3.9),
                new Product("SKU/003", "Mat", "SPORT", new BigDecimal("39.99"), 22, 1.9),
                new Product("SKU/004", "Keypad", "ELECTRONICS", new BigDecimal("139.99"), 4, 3.9),
                new Product("SKU/005", "Algorithms", "BOOK", new BigDecimal("79.99"), 256, 2.6),
                new Product("SKU/006", "Monitor", "ELECTRONICS", new BigDecimal("39.99"), 0, 4.5),
                new Product("SKU/007", "Bicycle", "SPORT", new BigDecimal("2139.99"), 6, 4.7),
                new Product("SKU/008", "Atomic habits", "BOOK", new BigDecimal("29.99"), 10, 3.9),
                new Product("SKU/009", "Laptop", "ELECTRONICS", new BigDecimal("2339.99"), 34, 5.0),
                new Product("SKU/010", "Bible", "BOOK", new BigDecimal("139.99"), 56, 5.0),
                new Product("SKU/011", "Sponge", "HOME", new BigDecimal("9.99"), 2220, 2.6),
                new Product("SKU/012", "Kettle", "SPORT", new BigDecimal("19.99"), 14, 4.0)
        );

        System.out.println("----Products sorted ascending by price----");
        List<Product> prodByPrice = products.stream()
                .sorted(Comparator.comparing(Product::price))
                .toList();
        prodByPrice.forEach(System.out::println);

        System.out.println("----Products sorted descending by rating----");
        List<Product> prodByRating = products.stream()
                .sorted(Comparator.comparing(Product::rating).reversed())
                .toList();
        prodByRating.forEach(System.out::println);

        System.out.println("----5 cheapest products----");
        List<String> cheapest5Products = products.stream()
                .sorted(Comparator.comparing(Product::price))
                .limit(5)
                .map(p -> p.name() + " - " + p.price() + " PLN")
                .toList();
        cheapest5Products.forEach(System.out::println);

        System.out.println("----ELECTRONICS sorted by rating descending then by price ascending----");
        List<Product> electronicsSortedByratingThenPrice = products.stream()
                .filter(p -> p.category().equals("ELECTRONICS"))
                .sorted(Comparator.comparing(Product::rating).reversed().thenComparing(Product::price))
                .toList();
        electronicsSortedByratingThenPrice.forEach(System.out::println);

        System.out.println("----Products in stock sorted by category then by name----");
        List<Product> inStockByCatThenByName = products.stream()
                .filter(p -> p.stock() > 0)
                .sorted(Comparator.comparing(Product::category).thenComparing(Product::name))
                .toList();
        inStockByCatThenByName.forEach(System.out::println);

        System.out.println("----Rank top 3 products with rating > 4.0----");
        List<String> top3BestRank = products.stream()
                .filter(p -> p.rating() > 4.0)
                .sorted(Comparator.comparing(Product::rating).reversed())
                .limit(3)
                .map(p -> p.name() + " (" + p.rating() + ")")
                .toList();

        IntStream.range(0, top3BestRank.size())
                .mapToObj(i -> "#" + (i + 1) + " " + top3BestRank.get(i))
                .forEach(System.out::println);

    }
}