package pl.kinga.wielowatkowosc.mini_project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("ORD-001", "Anna Kowalska", "Laptop Dell XPS", new BigDecimal("4999.99")),
                new Order("ORD-002", "Jan Nowak", "iPhone 15", new BigDecimal("5499.00")),
                new Order("ORD-003", "Maria Wisniewska", "Samsung TV 55\"", new BigDecimal("3299.00")),
                new Order("ORD-004", "Piotr Zielinski", "PlayStation 5", new BigDecimal("2499.00")),
                new Order("ORD-005", "Katarzyna Wozniak", "MacBook Air", new BigDecimal("5999.00")),
                new Order("ORD-006", "Tomasz Kaminski", "AirPods Pro", new BigDecimal("1299.00")),
                new Order("ORD-007", "Agnieszka Lewandowska", "iPad Pro", new BigDecimal("4799.00")),
                new Order("ORD-008", "Michal Szymanski", "Canon EOS R6", new BigDecimal("8999.00")),
                new Order("ORD-009", "Ewa Dabrowska", "Dyson V15", new BigDecimal("2899.00")),
                new Order("ORD-010", "Robert Jankowski", "Nike Air Max", new BigDecimal("699.00")),
                new Order("ORD-011", "Monika Majewska", "Kindle Paperwhite", new BigDecimal("699.00")),
                new Order("ORD-012", "Krzysztof Wojciechowski", "LG Monitor 27\"", new BigDecimal("1499.00")),
                new Order("ORD-013", "Joanna Kwiatkowska", "Bose QC45", new BigDecimal("1599.00")),
                new Order("ORD-014", "Adam Mazur", "Xbox Series X", new BigDecimal("2299.00")),
                new Order("ORD-015", "Natalia Krawczyk", "Apple Watch", new BigDecimal("1999.00"))
        );

        long startTime = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(4);


        OrderProcessor orderProcessor = new OrderProcessor();
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Order order : orders) {
            CompletableFuture<String> future = orderProcessor.processOrder(order, service);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();


        service.shutdown();
        try {
            if (!service.awaitTermination(30, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            service.shutdownNow();
        }
        orderProcessor.generateReport();
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + "ms");


    }
}
