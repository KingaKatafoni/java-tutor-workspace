package pl.kinga.funkcyjnajava.lekcja5_12.insurance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PolicyFinder {
    public static void main(String[] args) {
        List<InsurancePolicy> policies = List.of(
                new InsurancePolicy("POL001", "Anna Kowalska", "LIFE", new BigDecimal("320.00"),
                        LocalDate.of(2026, 12, 31), "Marek Zielinski"),
                new InsurancePolicy("POL002", "Jan Nowak", "CAR", new BigDecimal("890.00"),
                        LocalDate.of(2025, 6, 15), null),
                new InsurancePolicy("POL003", "Maria Wiszniewska", "HEALTH", new BigDecimal("450.00"),
                        LocalDate.of(2027, 3, 1), "Ewa Sikora"),
                new InsurancePolicy("POL004", "Piotr Zielinski", "HOME", new BigDecimal("560.00"),
                        LocalDate.of(2025, 9, 30), null),
                new InsurancePolicy("POL005", "Ewa Dabrowska", "CAR", new BigDecimal("1200.00"),
                        LocalDate.of(2026, 1, 15), "Marek Zielinski"),
                new InsurancePolicy("POL006", "Tomasz Lewandowski", "LIFE", new BigDecimal("280.00"),
                        LocalDate.of(2026, 8, 20), null),
                new InsurancePolicy("POL007", "Katarzyna Wojcik", "HEALTH", new BigDecimal("510.00"),
                        LocalDate.of(2027, 5, 10), "Anna Maj")
        );

        System.out.println("---- 1# Find policy by number ----");
        Optional<InsurancePolicy> findPolicy = policies.stream()
                .filter(p -> p.policyNumber().equals("POL003"))
                .findFirst();
        findPolicy.ifPresent(System.out::println);

        System.out.println("---- 2# Find non-existing policy POL099 ----");
        Optional<InsurancePolicy> findNonExistingPolicy = policies.stream()
                .filter(p -> p.policyNumber().equals("POL099"))
                .findFirst();

        findNonExistingPolicy.ifPresentOrElse(
                p -> System.out.println("Znaleziono: " + p),
                () -> System.out.println("Nie znaleziono"));


        System.out.println("---- 3# Find the cheapest car policy ----");
        InsurancePolicy theCheapestCarPolicy = policies.stream()
                .filter(p -> p.type().equals("CAR"))
                .min(Comparator.comparing(InsurancePolicy::premium))
                .orElseThrow(() -> new IllegalArgumentException("No CAR policy found"));

        System.out.println(theCheapestCarPolicy);

        System.out.println("---- 4# Agent name of policy number POL002 ----");
        InsurancePolicy pol002 = policies.stream()
                .filter(p -> p.policyNumber().equals("POL002"))
                .findFirst()
                .orElseThrow();

        String agentNamePol002 = Optional.ofNullable(pol002.agentName())
                .orElse("Kupiona online");
        System.out.println("Agent: " + agentNamePol002);

        System.out.println("---- 5# Agent name of policy number POL001 ----");
        InsurancePolicy pol001 = policies.stream()
                .filter(p -> p.policyNumber().equals("POL001"))
                .findFirst()
                .orElseThrow();
        String agentNamePol001 = Optional.ofNullable(pol001.agentName())
                .orElse("Kupiona online");
        System.out.println("Agent: " + agentNamePol001);

        System.out.println("---- 6# First policy expired after 2026-06-01 ----");
        InsurancePolicy policyExpired = policies.stream()
                .filter(p -> p.expiryDate().isAfter(LocalDate.of(2026, 6, 1)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No policy found"));
        System.out.println("Policy expired after 2026-06-01: " + policyExpired);

        System.out.println("---- 7# Handmade Optional ----");
        Optional<String> test = Optional.of("test");
        Optional<Object> nullable = Optional.ofNullable(null);
        Optional<Object> empty = Optional.empty();

        System.out.println("test isPresent: " + test.isPresent());
        System.out.println("test value: " + test.orElse("EMPTY"));
        System.out.println("nullable isPresent: " + nullable.isPresent());
        System.out.println("nullable value: " + nullable.orElse("EMPTY"));
        System.out.println("empty isPresent: " + empty.isPresent());
        System.out.println("empty value: " + empty.orElse("EMPTY"));

    }
}
