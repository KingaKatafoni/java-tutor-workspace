package pl.kinga.funkcyjnajava.lekcja5_14;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AntiPatternInsurance {
    record Policy(String id, String holder, String type, BigDecimal premium, boolean active) {
    }

    public static void main(String[] args) {
        List<Policy> policies = List.of(
                new Policy("P001", "Kowalska", "LIFE", new BigDecimal("300"), true),
                new Policy("P002", "Nowak", "CAR", new BigDecimal("800"), false),
                new Policy("P003", "Wiszniewska", "HEALTH", new BigDecimal("450"), true),
                new Policy("P004", "Zielinski", "CAR", new BigDecimal("1200"), true),
                new Policy("P005", "Dabrowska", "LIFE", new BigDecimal("280"), true),
                new Policy("P006", "Lewandowski", "HOME", new BigDecimal("560"), false)
        );

        // --- BLAD 1: filter + count zamiast anyMatch ---
        boolean hasCarPolicy = policies.stream()
                .anyMatch(p -> p.type().equals("CAR")); // filter + count zamiast anyMatch
        System.out.println("Has CAR: " + hasCarPolicy);

        // --- BLAD 2: side effect — modyfikowanie zewnetrznej listy ---
        List<String> activeHolders = policies.stream()
                .filter(Policy::active)
                .map(Policy::holder)
                .toList(); // zbieram Stringi do kolekcji a nie dodaje do już istniejącej zewnetrznej kolekcji
        System.out.println("Active: " + activeHolders);

        // --- BLAD 3: ponowne uzycie streamu ---
        long activeCount = policies.stream()
                .filter(Policy::active)
                .count(); // koncze metodą terminującą count() i to jest jeden stream
        List<Policy> activeList = policies.stream()
                .filter(Policy::active)
                .toList(); // kolejny stream zamknięty terminującą metodą toList()
        System.out.println("Active count: " + activeCount);

        // --- BLAD 4: isPresent + get ---
        Optional<Policy> maybeCheapest = policies.stream()
                .filter(Policy::active)
                .min(Comparator.comparing(Policy::premium)); // obecnie mamy metode ifPresent i możemy zmienić antypattern z get
        maybeCheapest.ifPresent(p -> System.out.println("Cheapest active: " + p.holder()));

        // --- BLAD 5: Optional.of na wartosci ktora moze byc null ---
        String agentName = null;  // symulacja — agent moze nie istniec

        Optional<String> agent = Optional.ofNullable(agentName);// musimy zmienic Optional.of na Optional.ofNullable bo String może być null

        // --- BLAD 6: zbyt dlugi lancuch bez zmiennych posrednich ---
        Stream<Policy> activeCarsSorted = policies.stream()
                .filter(Policy::active)
                .filter(p -> p.type().equals("CAR"))
                .sorted(Comparator.comparing(Policy::premium).reversed()); //osobno aktywne ubezpieczenia na CAR posortowane
        String collected = activeCarsSorted
                .map(p -> p.holder() + " (" + p.premium() + " PLN)")// formatowanie i collectowanie
                .collect(Collectors.joining(", "));
        System.out.println(collected);
    }
}
