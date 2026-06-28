package pl.kinga.funkcyjnajava.lekcja5_14;

import java.util.List;

public class AntiPatternTelecom {
    record Subscriber(String msisdn, String name, String plan, double dataGB, boolean active) {
    }

    record Department(String name, List<Subscriber> subscribers) {
    }

    public static void main(String[] args) {
        List<Department> departments = List.of(
                new Department("Warsaw", List.of(
                        new Subscriber("500100001", "Kowalska", "PREMIUM", 45.2, true),
                        new Subscriber("500100002", "Nowak", "BASIC", 2.1, false),
                        new Subscriber("500100003", "Wiszniewska", "STANDARD", 12.8, true)
                )),
                new Department("Krakow", List.of(
                        new Subscriber("500200001", "Zielinski", "PREMIUM", 67.3, true),
                        new Subscriber("500200002", "Dabrowska", "BASIC", 0.5, true)
                )),
                new Department("Poznan", List.of(
                        new Subscriber("500300001", "Lewandowski", "STANDARD", 18.9, true),
                        new Subscriber("500300002", "Wojcik", "PREMIUM", 33.1, false)
                ))
        );

        // --- BLAD 7: zagniezdzony stream zamiast flatMap ---
        System.out.println("All PREMIUM subscribers:");
        departments.stream()
                .flatMap(dept -> dept.subscribers.stream() //flatMap() i strumien Subscriber
                        .filter(s -> s.plan().equals("PREMIUM")))
                .forEach(s -> System.out.println(s.name()));// wyslwietlanie nie w zagniezdzonym strumieniu

        // --- BLAD 8: filter + count zamiast noneMatch ---
        boolean noInactive = departments.stream()
                .flatMap(d -> d.subscribers().stream())
                .allMatch(Subscriber::active); // allMatch z method reference
        System.out.println("No inactive: " + noInactive);

        // --- BLAD 9: side effect + zagniezdzony stream ---
        List<String> premiumNames = departments.stream()
                .flatMap(dept -> dept.subscribers().stream()
                        .filter(s -> s.plan().equals("PREMIUM") && s.active()))
                .map(Subscriber::name)
                .toList();

        System.out.println("Premium active: ");
        premiumNames.forEach(System.out::println);


        // --- BLAD 10: Optional<List> zamiast pustej listy ---
        List<Subscriber> maybeSubscribers =
                departments.stream()// nie opakowuje w Optional tworze stream ktory w wyniku bedzie listą (nawet pusta i to jest ok bo pusta kolekcja juz oznacza brak elemntow)
                        .flatMap(d -> d.subscribers().stream())
                        .filter(s -> s.plan().equals("ENTERPRISE"))
                        .toList();

        System.out.println("Enterprise: " + maybeSubscribers);
    }
}
