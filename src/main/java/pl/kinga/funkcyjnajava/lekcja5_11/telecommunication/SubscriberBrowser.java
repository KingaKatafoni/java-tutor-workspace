package pl.kinga.funkcyjnajava.lekcja5_11.telecommunication;

import java.util.Comparator;
import java.util.List;

public class SubscriberBrowser {
    public static void main(String[] args) {
        List<Subscriber> subscribers = List.of(
                new Subscriber("48500100001", "Anna", "Kowalska", "PREMIUM", 45.2, true),
                new Subscriber("48500100002", "Jan", "Nowak", "BASIC", 2.1, true),
                new Subscriber("48500100003", "Maria", "Wiszniewska", "STANDARD", 12.8, false),
                new Subscriber("48500100004", "Piotr", "Zielinski", "PREMIUM", 67.3, true),
                new Subscriber("48500100005", "Ewa", "Dabrowska", "BASIC", 0.5, true),
                new Subscriber("48500100006", "Tomasz", "Lewandowski", "STANDARD", 18.9, true),
                new Subscriber("48500100007", "Katarzyna", "Wojcik", "PREMIUM", 33.1, true),
                new Subscriber("48500100008", "Michal", "Kaminski", "BASIC", 1.8, false),
                new Subscriber("48500100009", "Agnieszka", "Kozlowska", "STANDARD", 8.4, true),
                new Subscriber("48500100010", "Robert", "Jankowski", "PREMIUM", 52.0, true),
                new Subscriber("48500100011", "Dorota", "Baran", "BASIC", 3.3, true),
                new Subscriber("48500100012", "Emil", "Lis", "STANDARD", 15.7, false)
        );

        System.out.println("----1# top 3 users with data usage----");
        subscribers.stream()
                .sorted(Comparator.comparing(Subscriber::dataUsageGB).reversed())
                .limit(3)
                .forEach(s -> System.out.println(s.firstName() + " " + s.lastName() + " (" + s.dataUsageGB() + " GB)"));

        System.out.println("----2# All minus the smallest usage----");
        subscribers.stream()
                .sorted(Comparator.comparing(Subscriber::dataUsageGB))
                .skip(2)
                .forEach(System.out::println);

        System.out.println("----3# Pagination 5 subscriber per page----");
        int pageSize = 5;
        List<Subscriber> page0 = subscribers.stream()
                .sorted(Comparator.comparing(Subscriber::lastName))
                .skip(0)
                .limit(pageSize)
                .toList();
        List<Subscriber> page1 = subscribers.stream()
                .sorted(Comparator.comparing(Subscriber::lastName))
                .skip(pageSize)
                .limit(pageSize)
                .toList();

        System.out.println("----Page 0----");
        page0.forEach(System.out::println);
        System.out.println("----Page 1----");
        page1.forEach(System.out::println);

        System.out.println("----4# Debugging PREMIUM plan----");
        List<Subscriber> debugging = subscribers.stream()
                .filter(Subscriber::active)
                .filter(s -> s.plan().equals("PREMIUM"))
                .peek(t -> System.out.println("Checking " + t.msisdn()))
                .sorted(Comparator.comparing(Subscriber::dataUsageGB).reversed())
                .toList();
        debugging.forEach(System.out::println);

        System.out.println("----5# Active users Basic----");
        List<Subscriber> first = subscribers.stream()
                .filter(Subscriber::active)
                .filter(s -> s.plan().equals("BASIC"))
                .sorted(Comparator.comparing(Subscriber::lastName))
                .limit(1)
                .toList();
        List<Subscriber> last = subscribers.stream()
                .filter(Subscriber::active)
                .filter(s -> s.plan().equals("BASIC"))
                .sorted(Comparator.comparing(Subscriber::lastName).reversed())
                .limit(1)
                .toList();
        first.forEach(s -> System.out.println("First: " + s));
        last.forEach(s -> System.out.println("Last: " + s));

        System.out.println("----6# Preview 3 random records----");
        List<Subscriber> randomRecords = subscribers.stream()
                .limit(3)
                .toList();
        randomRecords.forEach(System.out::println);

    }
}
