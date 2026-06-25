package pl.kinga.funkcyjnajava.lekcja5_9.ecommerce;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderAnalyzer {
    public static void main(String[] args) {
        List<CustomerOrder> orders = List.of(
                new CustomerOrder("ORD001", "Anna Kowalska", "ELECTRONICS", new BigDecimal("2499.99"), 1, "DELIVERED"),
                new CustomerOrder("ORD002", "Jan Nowak", "BOOKS", new BigDecimal("89.97"), 3, "DELIVERED"),
                new CustomerOrder("ORD003", "Anna Kowalska", "CLOTHING", new BigDecimal("349.98"), 2, "SHIPPED"),
                new CustomerOrder("ORD004", "Maria Wiszniewska", "FOOD", new BigDecimal("124.50"), 5, "NEW"),
                new CustomerOrder("ORD005", "Piotr Zielinski", "ELECTRONICS", new BigDecimal("5999.00"), 2, "PROCESSING"),
                new CustomerOrder("ORD006", "Jan Nowak", "ELECTRONICS", new BigDecimal("799.00"), 1, "DELIVERED"),
                new CustomerOrder("ORD007", "Ewa Dabrowska", "BOOKS", new BigDecimal("45.99"), 1, "SHIPPED"),
                new CustomerOrder("ORD008", "Tomasz Lewandowski", "CLOTHING", new BigDecimal("599.97"), 3, "NEW"),
                new CustomerOrder("ORD009", "Anna Kowalska", "FOOD", new BigDecimal("67.80"), 4, "DELIVERED"),
                new CustomerOrder("ORD010", "Piotr Zielinski", "BOOKS", new BigDecimal("159.98"), 2, "SHIPPED"),
                new CustomerOrder("ORD011", "Maria Wiszniewska", "ELECTRONICS", new BigDecimal("1299.00"), 1, "PROCESSING"),
                new CustomerOrder("ORD012", "Ewa Dabrowska", "FOOD", new BigDecimal("234.00"), 6, "DELIVERED"),
                new CustomerOrder("ORD013", "Tomasz Lewandowski", "BOOKS", new BigDecimal("29.99"), 1, "DELIVERED"),
                new CustomerOrder("ORD014", "Jan Nowak", "CLOTHING", new BigDecimal("179.99"), 1, "NEW"),
                new CustomerOrder("ORD015", "Piotr Zielinski", "FOOD", new BigDecimal("89.90"), 3, "SHIPPED")
        );

        System.out.println("----1# Orders grouped by status----");
        Map<String, Long> ordersPerStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::status,
                        Collectors.counting()
                ));
        ordersPerStatus.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----2# Total value of orders per category----");
        Map<String, BigDecimal> totalValuePerCat = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::category,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                CustomerOrder::totalPrice,
                                BigDecimal::add
                        )
                ));
        totalValuePerCat.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----3# Item count per category----");
        Map<String, Integer> itemCountPerCat = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::category,
                        Collectors.summingInt(CustomerOrder::itemCount)
                ));

        itemCountPerCat.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----4# Client name per category----");
        Map<String, Set<String>> namePerCat = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::category,
                        Collectors.mapping(
                                CustomerOrder::customerName,
                                Collectors.toSet()
                        )
                ));
        namePerCat.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----5# Partitioning by DELIVERED/UNDELIVERED----");
        Map<Boolean, Integer> partitioningByStatus = orders.stream()
                .collect(Collectors.partitioningBy(
                        o -> o.status().equals("DELIVERED"),
                        Collectors.summingInt(e -> 1)
                ));
        partitioningByStatus.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----6# Average value of order per category----");
        Map<String, BigDecimal> sumPerCat = orders.stream()
                .collect(
                        Collectors.groupingBy(
                                CustomerOrder::category,
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        CustomerOrder::totalPrice,
                                        BigDecimal::add
                                ))
                );

        Map<String, Long> count = orders.stream()
                .collect(
                        Collectors.groupingBy(CustomerOrder::category,
                                Collectors.counting())
                );

        Map<String, BigDecimal> average = sumPerCat.keySet().stream()
                .collect(Collectors.toMap(
                        k -> k,
                        k -> sumPerCat.get(k).divide(BigDecimal.valueOf(count.get(k)), 2, RoundingMode.HALF_UP)
                ));
        average.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----7# Id list per client----");
        Map<String, List<String>> idListPerClient = orders.stream()
                .collect(Collectors.groupingBy(
                        CustomerOrder::customerName,
                        Collectors.mapping(
                                CustomerOrder::orderId,
                                Collectors.toList()
                        )
                ));
        idListPerClient.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}
