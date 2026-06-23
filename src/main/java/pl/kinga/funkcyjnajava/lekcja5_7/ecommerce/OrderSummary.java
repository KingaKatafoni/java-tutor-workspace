package pl.kinga.funkcyjnajava.lekcja5_7.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OrderSummary {
    public static void main(String[] args) {
        List<OrderItem> items = List.of(
                new OrderItem("ORD/001", "Laptop", 4, new BigDecimal("1234.87"), "PENDING"),
                new OrderItem("ORD/002", "Car", 99, new BigDecimal("123456.99"), "SHIPPED"),
                new OrderItem("ORD/003", "Book", 4, new BigDecimal("234.87"), "DELIVERED"),
                new OrderItem("ORD/004", "Pillow", 3, new BigDecimal("6.99"), "SHIPPED"),
                new OrderItem("ORD/005", "Laptop", 45, new BigDecimal("3234.87"), "PENDING"),
                new OrderItem("ORD/006", "Car", 9, new BigDecimal("1223456.99"), "CANCELLED"),
                new OrderItem("ORD/007", "Pillow", 499, new BigDecimal("234.87"), "PENDING"),
                new OrderItem("ORD/008", "Cloths", 92, new BigDecimal("456.99"), "SHIPPED"),
                new OrderItem("ORD/009", "School bag", 145, new BigDecimal("56.99"), "DELIVERED"),
                new OrderItem("ORD/010", "Vacuum", 969, new BigDecimal("156.99"), "SHIPPED"),
                new OrderItem("ORD/011", "Kite", 9932, new BigDecimal("5.99"), "DELIVERED"),
                new OrderItem("ORD/012", "Fliers", 2, new BigDecimal("2.99"), "CANCELLED")
        );

        System.out.println("----Total value of DELIVERED orders----");
        BigDecimal totalValueDelivered = items.stream()
                .filter(o -> o.status().equals("DELIVERED"))
                .map(o -> o.unitPrice().multiply(BigDecimal.valueOf(o.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalValueDelivered);

        System.out.println("----Mean value of orders----");
        BigDecimal sumOfValueQuantity = items.stream()
                .map(o -> o.unitPrice().multiply(BigDecimal.valueOf(o.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long quantityOfOrders = items.size();
        BigDecimal meanValue = sumOfValueQuantity.divide(BigDecimal.valueOf(quantityOfOrders), 2, RoundingMode.HALF_UP);
        System.out.println(meanValue);

        System.out.println("----The most expensive order----");
        Optional<OrderItem> mostExpensiveOrder = items.stream()
                .max(Comparator.comparing(o -> o.unitPrice().multiply(BigDecimal.valueOf(o.quantity()))));
        mostExpensiveOrder.ifPresent(System.out::println);

        System.out.println("----The cheapest order not cancelled----");
        Optional<OrderItem> cheapestOrderNotCancelled = items.stream()
                .filter(o -> !o.status().equals("CANCELLED"))
                .min(Comparator.comparing(o -> o.unitPrice().multiply(BigDecimal.valueOf(o.quantity()))));
        cheapestOrderNotCancelled.ifPresent(System.out::println);

        System.out.println("----Sum quantity----");
        int sumQuantity = items.stream()
                .mapToInt(OrderItem::quantity)
                .sum();
        System.out.println(sumQuantity);

        System.out.println("----Sum delivered quantity----");
        int sumDeliveredQuantity = items.stream()
                .filter(o -> o.status().equals("DELIVERED"))
                .mapToInt(OrderItem::quantity)
                .sum();
        System.out.println(sumDeliveredQuantity);

        System.out.println("----Product List----");
        String uniqueProduct = items.stream()
                .map(OrderItem::productName)
                .distinct()
                .reduce((a, b) -> a + ", " + b)
                .orElse("brak");
        System.out.println(uniqueProduct);

        System.out.println("----Value of Cancelled order----");
        Optional<BigDecimal> cancelledOrderValue = items.stream()
                .filter(o -> o.status().equals("CANCELLED"))
                .map(o -> o.unitPrice().multiply(BigDecimal.valueOf(o.quantity())))
                .reduce(BigDecimal::add);
        cancelledOrderValue.ifPresent(System.out::println);
    }
}
