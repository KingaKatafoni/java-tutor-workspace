package pl.kinga.wielowatkowosc.mini_project;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderProcessor {
    AtomicInteger processedCount = new AtomicInteger(0);
    AtomicInteger failedCount = new AtomicInteger(0);
    ConcurrentHashMap<String, String> orderStatuses = new ConcurrentHashMap<>();

    public String validate(Order order) {
        int sleepTime = 200 + ThreadLocalRandom.current().nextInt(300);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (ThreadLocalRandom.current().nextInt(100) < 10) {
            throw new RuntimeException("Validation failed: product unavailable");
        } else {
            return order.getOrderId() + ": validated";
        }
    }

    public String processPayment(Order order) {
        int sleepTime = 200 + ThreadLocalRandom.current().nextInt(300);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (ThreadLocalRandom.current().nextInt(100) < 15) {
            throw new RuntimeException("Payment failed: insufficient funds");
        } else {
            return order.getOrderId() + ": payment processed";
        }
    }

    public String shipOrder(Order order) {
        int sleepTime = 400 + ThreadLocalRandom.current().nextInt(300);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (ThreadLocalRandom.current().nextInt(100) < 5) {
            throw new RuntimeException("Shipping failed: address invalid");
        } else {
            return order.getOrderId() + ": shipped";
        }
    }

    public CompletableFuture<String> processOrder(Order order, ExecutorService executor) {

        return CompletableFuture
                .supplyAsync(() -> validate(order), executor)
                .thenApply(r -> processPayment(order))
                .thenApply(r -> shipOrder(order))
                .thenApply(r -> {
                    processedCount.incrementAndGet();
                    orderStatuses.put(order.getOrderId(), "SHIPPED");
                    return r;
                })
                .exceptionally(ex -> {
                            failedCount.incrementAndGet();
                            orderStatuses.put(order.getOrderId(), "FAILED");
                            return "FAILED: " + order.getOrderId();
                        }
                );
    }

    public void generateReport() {
        double rate = (double) processedCount.get() / (processedCount.get() + failedCount.get()) * 100;
        StringBuilder report = new StringBuilder();
        report.append("=== Order Processing Report ===\n");
        report.append("Total processed: ")
                .append(processedCount);
        report.append("\nFailed: ")
                .append(failedCount);
        report.append("\nSuccess rate: ")
                .append(String.format("%.2f%%", rate))
                .append("\n");

        report.append("\nOrder statuses:\n");
        orderStatuses.forEach(
                (k, v) ->
                        report.append(k)
                                .append(": ")
                                .append(v)
                                .append("\n")
        );
        System.out.println(report);
    }
}
