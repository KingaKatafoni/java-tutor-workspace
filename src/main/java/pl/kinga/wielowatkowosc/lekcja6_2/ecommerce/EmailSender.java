package pl.kinga.wielowatkowosc.lekcja6_2.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class EmailSender {
    record Order(String orderId, String customerEmail) {
    }

    public static void main(String[] args) throws InterruptedException {
        List<Order> orders = List.of(
                new Order("ORD001", "kowalska@mail.pl"),
                new Order("ORD002", "nowak@mail.pl"),
                new Order("ORD003", "wiszniewska@mail.pl"),
                new Order("ORD004", "zielinski@mail.pl"),
                new Order("ORD005", "dabrowska@mail.pl")
        );

        List<Thread> threads = new ArrayList<>();
        orders.forEach(o -> {

            Thread th = new Thread(() -> {
                System.out.println("Sending email to " + o.customerEmail + "... " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Email sent to " + o.customerEmail + " " + Thread.currentThread().getName());

            }
            );
            th.setName("email-sender " + o.orderId);
            th.start();
            threads.add(th);
        });

        threads.forEach((th -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        System.out.println("All 5 emails sent");

        //Przy kazdym wywolaniu kolejnosc jest inna
    }
}
