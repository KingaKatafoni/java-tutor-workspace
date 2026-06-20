package pl.kinga.funkcyjnajava.lekcja5_6.publicsector;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class OfficerRanking {
    public static void main(String[] args) {
        List<Officer> officers = List.of(
                new Officer("OFFI/001", "Katarzyna", "Wawrzyk", "FINANCE", "INTERN", new BigDecimal("3456.00"), 4),
                new Officer("OFFI/002", "Piotr", "Adamczyk", "LEGAL", "SPECIALIST", new BigDecimal("10456.00"), 10),
                new Officer("OFFI/003", "Jagna", "Babicz", "FINANCE", "SENIOR_SPECIALIST", new BigDecimal("7456.00"), 2),
                new Officer("OFFI/004", "Jakub", "Obibok", "IT", "MANAGER", new BigDecimal("14456.00"), 12),
                new Officer("OFFI/005", "Bogna", "Zapad", "IT", "INTERN", new BigDecimal("5456.00"), 6),
                new Officer("OFFI/006", "Krystyna", "Bok", "FINANCE", "INTERN", new BigDecimal("4456.00"), 1),
                new Officer("OFFI/007", "Aleksandra", "Rawa", "MANAGEMENT", "SPECIALIST", new BigDecimal("10456.00"), 17),
                new Officer("OFFI/008", "Euzebia", "Cerko", "FINANCE", "SENIOR_SPECIALIST", new BigDecimal("18456.00"), 20),
                new Officer("OFFI/009", "Malwina", "Wartko", "IT", "MANAGER", new BigDecimal("23456.00"), 14),
                new Officer("OFFI/010", "Eryk", "Albinek", "MANAGEMENT", "DIRECTOR", new BigDecimal("53456.00"), 30),
                new Officer("OFFI/011", "Benjamin", "Dyko", "FINANCE", "SENIOR_SPECIALIST", new BigDecimal("17456.00"), 10),
                new Officer("OFFI/012", "Kacper", "Malik", "LEGAL", "SPECIALIST", new BigDecimal("20456.00"), 15)
        );

        System.out.println("----Officers Alphabetically Sorted----");
        List<Officer> officersAlphSorted = officers.stream()
                .sorted(Comparator.comparing(Officer::lastName))
                .toList();
        officersAlphSorted.forEach(System.out::println);

        System.out.println("----Salary Descending----");
        List<Officer> salaryDescendingSorted = officers.stream()
                .sorted(Comparator.comparing(Officer::salary).reversed())
                .toList();
        salaryDescendingSorted.forEach(System.out::println);

        System.out.println("----Department ascending, Salary descending----");
        List<Officer> depAscSalDesc = officers.stream()
                .sorted(Comparator.comparing(Officer::department)
                        .thenComparing(Officer::salary, Comparator.reverseOrder()))
                .toList();
        depAscSalDesc.forEach(System.out::println);

        System.out.println("----Top 3 best paid Officers----");
        List<Officer> bestPaidOfficers = officers.stream()
                .sorted(Comparator.comparing(Officer::salary).reversed())
                .limit(3)
                .toList();
        bestPaidOfficers.forEach(System.out::println);

        System.out.println("----IT Officers by years of service----");
        List<String> itOfficersByYearsOfService = officers.stream()
                .filter(o -> o.department().equals("IT"))
                .sorted(Comparator.comparing(Officer::yearsOfService).reversed())
                .map(o -> o.firstName() + " " + o.lastName() + " - " + o.yearsOfService() + " years")
                .toList();
        itOfficersByYearsOfService.forEach(System.out::println);

        System.out.println("----All sorted by rang then last name----");
        List<Officer> rangThenLastName = officers.stream()
                .sorted(Comparator.comparing(Officer::rank).thenComparing(Officer::lastName))
                .toList();
        rangThenLastName.forEach(System.out::println);

        System.out.println("----Unique department sorted alphabetically----");
        List<String> uniqueDepartment = officers.stream()
                .map(Officer::department)
                .sorted()
                .distinct()
                .toList();
        uniqueDepartment.forEach(System.out::println);
    }
}
