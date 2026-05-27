package pl.kinga.kolekcjegeneryki.ecommerce.ecommercecompare;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.addProduct(new Product("Car", new BigDecimal("300000.00"), 2.5));
        productCatalog.addProduct(new Product("Mouse", new BigDecimal("300.00"), 4.5));
        productCatalog.addProduct(new Product("Bag", new BigDecimal("10000.00"), 1.5));
        productCatalog.addProduct(new Product("Bag", new BigDecimal("30000.00"), 4.5));
        productCatalog.addProduct(new Product("House", new BigDecimal("1300000.00"), 2.5));
        productCatalog.addProduct(new Product("Laptop", new BigDecimal("1000.00"), 3.5));
        System.out.println("-----All products--------");
        productCatalog.printAll();
        System.out.println();
        System.out.println("----sortByPriceAscending()-----");
        productCatalog.sortByPriceAscending();
        productCatalog.printAll();
        System.out.println();
        System.out.println("-----sortByPriceDescending()-----");
        productCatalog.sortByPriceDescending();
        productCatalog.printAll();
        System.out.println();
        System.out.println("-----sortByNameThanPrice()------");
        productCatalog.sortByNameThenPrice();
        productCatalog.printAll();
        System.out.println();
        System.out.println("-----sortByRatingDescending()-------");
        productCatalog.sortByRatingDescending();
        productCatalog.printAll();
    }
}
