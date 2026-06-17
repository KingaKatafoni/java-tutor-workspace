package pl.kinga.funkcyjnajava.lekcja5_1.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrderProcessor {
    public static int countMatching(List<Order> orders, Predicate<Order> condition) {
        int count = 0;
        for (Order o : orders) {
            if (condition.test(o)) {
                count++;
            }
        }
        return count;
    }

    public static List<String> summarize(List<Order> orders, Function<Order, String> formatter) {
        List<String> result = new ArrayList<>();
        for (Order o : orders) {
            result.add(formatter.apply(o));
        }
        return result;
    }

    public static void process(List<Order> orders, Predicate<Order> filter, Consumer<Order> action) {
        for (Order o : orders) {
            if (filter.test(o)) {
                action.accept(o);
            }
        }
    }

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("O/001", "Jan Kowalski", new BigDecimal("450.99"), "PENDING", "ELECTRONICS"));
        orders.add(new Order("O/002", "Bogumił Adamczych", new BigDecimal("29.99"), "COMPLETED", "BOOKS"));
        orders.add(new Order("O/003", "Olga Boznanska", new BigDecimal("1500.99"), "PENDING", "CLOTHING"));
        orders.add(new Order("O/004", "Maksymilian Skalski", new BigDecimal("14450.99"), "CANCELLED", "ELECTRONICS"));
        orders.add(new Order("O/005", "Franciszka Maj", new BigDecimal("3050.99"), "PENDING", "ELECTRONICS"));
        orders.add(new Order("O/006", "Lucyna Bazgroł", new BigDecimal("2450.99"), "COMPLETED", "CLOTHING"));
        orders.add(new Order("O/007", "Olaf Cyrulik", new BigDecimal("50.99"), "PENDING", "BOOKS"));
        orders.add(new Order("O/008", "Barbara Kasprzyk", new BigDecimal("45.99"), "CANCELLED", "ELECTRONICS"));

        System.out.println("Completed orders: " + countMatching(orders, c -> c.status().equals("COMPLETED")));
        System.out.println("Orders above 500 PLN: " + countMatching(orders, c -> c.amount().compareTo(new BigDecimal("500.00")) > 0));
        summarize(orders, c -> "[" + c.id() + "] " + c.customerName() + " - " + c.amount() + " PLN").forEach(System.out::println);


        process(orders, c -> c.status().equals("PENDING"), c -> System.out.println("⚠ Pending: " + c.id() + " - " + c.customerName()));
    }
}
