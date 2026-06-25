package pl.kinga.funkcyjnajava.lekcja5_9.publicsector;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TaxAnalyzer {
    public static void main(String[] args) {
        List<TaxRecord> records = List.of(
                new TaxRecord("NIP001", "Anna Kowalska", "Warszawa", "PIT", new BigDecimal("4500.00"), 2023, true),
                new TaxRecord("NIP002", "Jan Nowak", "Warszawa", "CIT", new BigDecimal("12000.00"), 2023, false),
                new TaxRecord("NIP003", "Maria Wiszniewska", "Krakow", "PIT", new BigDecimal("3200.00"), 2024, true),
                new TaxRecord("NIP004", "Piotr Zielinski", "Krakow", "VAT", new BigDecimal("8900.00"), 2024, false),
                new TaxRecord("NIP005", "Ewa Dabrowska", "Poznan", "PIT", new BigDecimal("5100.00"), 2025, true),
                new TaxRecord("NIP006", "Tomasz Lewandowski", "Poznan", "CIT", new BigDecimal("22000.00"), 2023, false),
                new TaxRecord("NIP007", "Katarzyna Wojcik", "Warszawa", "VAT", new BigDecimal("6700.00"), 2024, true),
                new TaxRecord("NIP008", "Michal Kaminski", "Krakow", "PIT", new BigDecimal("3800.00"), 2025, false),
                new TaxRecord("NIP009", "Agnieszka Kozlowska", "Gdansk", "CIT", new BigDecimal("15500.00"), 2023, true),
                new TaxRecord("NIP010", "Robert Jankowski", "Gdansk", "VAT", new BigDecimal("9200.00"), 2024, false),
                new TaxRecord("NIP011", "Anna Kowalska", "Warszawa", "PIT", new BigDecimal("4800.00"), 2024, true),
                new TaxRecord("NIP012", "Jan Nowak", "Warszawa", "VAT", new BigDecimal("7300.00"), 2025, true),
                new TaxRecord("NIP013", "Ewa Dabrowska", "Poznan", "VAT", new BigDecimal("4100.00"), 2025, false),
                new TaxRecord("NIP014", "Piotr Zielinski", "Krakow", "CIT", new BigDecimal("18000.00"), 2023, true),
                new TaxRecord("NIP015", "Tomasz Lewandowski", "Poznan", "PIT", new BigDecimal("2900.00"), 2024, false)
        );

        System.out.println("----1# Taxpayers grouped by city----");
        Map<String, List<String>> taxpayersPerCity = records.stream()
                .collect(Collectors.groupingBy(TaxRecord::city, Collectors.mapping(TaxRecord::fullName, Collectors.toList())));
        taxpayersPerCity.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("----2# Number of records per type----");
        Map<String, Long> countByType = records.stream()
                .collect(Collectors.groupingBy(TaxRecord::taxType, Collectors.counting()));
        countByType.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("----3# Total amount of taxes per year----");
        Map<Integer, BigDecimal> totalTaxAmountPerYear = records.stream()
                .collect(Collectors.groupingBy(
                                TaxRecord::year,
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        TaxRecord::amount,
                                        BigDecimal::add
                                )
                        )
                );
        totalTaxAmountPerYear.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----4# Names per types----");
        Map<String, String> namesPerType = records.stream()
                .collect(Collectors.groupingBy(
                        TaxRecord::taxType,
                        Collectors.mapping(
                                TaxRecord::fullName,
                                Collectors.joining(", "))));
        namesPerType.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----5# Division by paid/unpaid----");
        Map<Boolean, Long> paidUnpaid = records.stream()
                .collect(Collectors.partitioningBy(
                        TaxRecord::paid,
                        Collectors.counting()
                        ));
        paidUnpaid.forEach((k, v) -> System.out.println(k + " → " + v));

        System.out.println("----6# Total amount of unpaid tax per city----");
        Map<String, BigDecimal> totalAmountUnpaidPerCity = records.stream()
                .filter(r -> !r.paid())
                .collect(Collectors.groupingBy(
                        TaxRecord::city,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                TaxRecord::amount,
                                BigDecimal::add
                        )
                ));
        totalAmountUnpaidPerCity.forEach((k, v) -> System.out.println(k + " → " + v + " PLN"));

        System.out.println("----7# Unique city per type----");
        Map<String, Set<String>> uniqueCityPerType = records.stream()
                .collect(
                        Collectors.groupingBy(
                                TaxRecord::taxType,
                                Collectors.mapping(
                                        TaxRecord::city,
                                        Collectors.toSet()
                                )
                        )
                );
        uniqueCityPerType.forEach((k, v) -> System.out.println(k + " → " + v));


    }
}
