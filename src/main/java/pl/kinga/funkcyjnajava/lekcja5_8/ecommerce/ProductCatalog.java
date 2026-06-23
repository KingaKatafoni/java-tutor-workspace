package pl.kinga.funkcyjnajava.lekcja5_8.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ProductCatalog {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("SKU/001", "Clean Code", "BOOK", new BigDecimal("39.99"), 2, true),
                new Product("SKU/002", "Console", "ELECTRONICS", new BigDecimal("1239.99"), 0, true),
                new Product("SKU/003", "Skirt", "CLOTHING", new BigDecimal("39.99"), 22, false),
                new Product("SKU/004", "Keypad", "ELECTRONICS", new BigDecimal("139.99"), 4, true),
                new Product("SKU/005", "Algorithms", "BOOK", new BigDecimal("79.99"), 256, false),
                new Product("SKU/006", "Monitor", "ELECTRONICS", new BigDecimal("39.99"), 0, true),
                new Product("SKU/007", "Bread", "FOOD", new BigDecimal("2139.99"), 6, true),
                new Product("SKU/008", "Atomic habits", "BOOK", new BigDecimal("29.99"), 10, false),
                new Product("SKU/009", "Laptop", "ELECTRONICS", new BigDecimal("2339.99"), 34, false),
                new Product("SKU/010", "Bible", "BOOK", new BigDecimal("139.99"), 56, true),
                new Product("SKU/011", "Sponge", "HOME", new BigDecimal("9.99"), 2220, false),
                new Product("SKU/012", "Pineapple", "FOOD", new BigDecimal("19.99"), 14, true)
        );

        System.out.println("----1# Active products sorted by price ascending----");
        List<String> activeByPrice = products.stream()
                .filter(Product::active)
                .sorted(Comparator.comparing(Product::price))
                .map(Product::name)
                .toList();
        activeByPrice.forEach(System.out::println);

        System.out.println("----2# Unique Categories----");
        Set<String> uniqueCategories = products.stream()
                .map(Product::category)
                .collect(Collectors.toSet());
        uniqueCategories.forEach(System.out::println);

        System.out.println("----3# Map SKU → name----");
        Map<String, String> skuName = products.stream()
                .collect(Collectors.toMap(Product::sku, Product::name));
        skuName.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----4# Category → the cheapest----");
        Map<String, BigDecimal> categoryCheapest = products.stream()
                .collect(Collectors.toMap(Product::category, Product::price, BinaryOperator.minBy(Comparator.naturalOrder())));
        categoryCheapest.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----5# Category → total stock value----");
        Map<String, BigDecimal> categoryTotalStock = products.stream()
                .collect(Collectors.toMap(Product::category, p -> p.price().multiply(BigDecimal.valueOf(p.stockQuantity())), BigDecimal::add));
        categoryTotalStock.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----6# Products with zero in stock----");
        String productsWithZero = products.stream()
                .filter(p -> p.stockQuantity() == 0)
                .map(Product::name)
                .collect(Collectors.joining(", ", "Brak na stanie: [", "]"));
        System.out.println(productsWithZero);

        System.out.println("----7# Active/Inactive → names list----");
        List<String> active = products.stream()
                .filter(Product::active)
                .map(Product::name)
                .toList();
        List<String> inactive = products.stream()
                .filter(p -> !p.active())
                .map(Product::name)
                .toList();
        System.out.println("→ Active");
        active.forEach(System.out::println);
        System.out.println("→ Inactive");
        inactive.forEach(System.out::println);

        System.out.println("----8# Average price of all products----");
        BigDecimal sumAllProducts = products.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averagePrice = sumAllProducts.divide(BigDecimal.valueOf(products.size()),2, RoundingMode.HALF_UP);
        System.out.println(averagePrice + " PLN");

    }
}
