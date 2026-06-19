package pl.kinga.funkcyjnajava.lekcja5_5.logistics;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class CourierAnalyzer {
    public static void main(String[] args) {
        List<CourierPackage> packages = List.of(
                new CourierPackage("PACK/001", "Kamil Bozejko", "Zygmunt Stary", "Poznan", 12.00, new BigDecimal("250.00"), "NEW"),
                new CourierPackage("PACK/002", "Andrzej Dragan", "Halina Petlina", "Radom", 142.00, new BigDecimal("2150.00"), "IN_TRANSIT"),
                new CourierPackage("PACK/003", "Malwina Borowina", "Monika Robika", "Poznan", 62.00, new BigDecimal("1250.00"), "DELIVERED"),
                new CourierPackage("PACK/004", "Paulina Zab", "Weronika Koszyk", "Warszawa", 2.00, new BigDecimal("50.00"), "IN_TRANSIT"),
                new CourierPackage("PACK/005", "Mateusz Pateusz", "Abelard Ujek", "Wroclaw", 112.00, new BigDecimal("20.00"), "NEW"),
                new CourierPackage("PACK/006", "Kamil Bozejko", "Norbert Kes", "Gdansk", 23.00, new BigDecimal("150.00"), "DELIVERED"),
                new CourierPackage("PACK/007", "Lucyna Fujka", "Leokadia Piuk", "Kalisz", 10.00, new BigDecimal("1350.00"), "LOST"),
                new CourierPackage("PACK/008", "Maurycy Donica", "Matylda Erst", "Gniezno", 16.00, new BigDecimal("25220.00"), "NEW"),
                new CourierPackage("PACK/009", "Amadeusz Mozart", "Zenobia Lut", "Konin", 54.00, new BigDecimal("650.00"), "LOST"),
                new CourierPackage("PACK/010", "Aldona Ilonak", "Abelard Ujek", "Wroclaw", 22.00, new BigDecimal("9050.00"), "IN_TRANSIT"),
                new CourierPackage("PACK/011", "Radowlaw Dzwon", "Zygmunt Stary", "Poznan", 28.00, new BigDecimal("350.00"), "DELIVERED")
        );

        System.out.println("----Packages to Wawszawa IN_TRNSIT----");
        List<CourierPackage> packagesToWarInTransit = packages.stream()
                .filter(p -> p.status().equals("IN_TRANSIT"))
                .filter(p -> p.receiverCity().equals("Warszawa"))
                .toList();
        packagesToWarInTransit.forEach(System.out::println);

        System.out.println("----Receiver Names of Delivered packages----");
        List<String> receiverNamesDelivered = packages.stream()
                .filter(p -> p.status().equals("DELIVERED"))
                .map(CourierPackage::receiverName)
                .toList();
        receiverNamesDelivered.forEach(System.out::println);

        System.out.println("----Total weight packages in transit----");
        double totalSumOfWeightInTransit = packages.stream()
                .filter(p -> p.status().equals("IN_TRANSIT"))
                .mapToDouble(CourierPackage::weight)
                .sum();
        System.out.println("Total weight packages in transit: " + totalSumOfWeightInTransit);

        System.out.println("----Average declared value of all packages----");
        double averageDeclaredValueOfAll = packages.stream()
                .mapToDouble(p -> p.declaredValue().doubleValue())
                .average()
                .orElse(0.0);

        System.out.printf("Average declared value of all packages: %.2f%n", averageDeclaredValueOfAll);

        System.out.println("----Heaviest Package----");
        double heaviestPackage = packages.stream()
                .mapToDouble(CourierPackage::weight)
                .max()
                .orElse(0.0);

        System.out.printf("Heaviest Package: %.2f%n", heaviestPackage);

        System.out.println("----Statistics of declared values----");
        DoubleSummaryStatistics statisticsDeclaredValue = packages.stream()
                .mapToDouble(p -> p.declaredValue().doubleValue())
                .summaryStatistics();
        System.out.printf("Average: %.2f PLN%n", statisticsDeclaredValue.getAverage());
        System.out.println("Count: " + statisticsDeclaredValue.getCount());
        System.out.printf("Min: %.2f PLN%n", statisticsDeclaredValue.getMin());
        System.out.printf("Max: %.2f PLN%n", statisticsDeclaredValue.getMax());

        System.out.println("----Amount of lost packages----");
        long countLost = packages.stream()
                .filter(p -> p.status().equals("LOST"))
                .count();
        System.out.println(countLost);

        System.out.println("----Label of new packages----");
        List<String> labelNew = packages.stream()
                .filter(p -> p.status().equals("NEW"))
                .map(p -> "[" + p.packageId() + "] To: " + p.receiverName() + ", " + p.receiverCity() + " (" + p.weight() + " kg)")
                .toList();
        labelNew.forEach(System.out::println);

        System.out.println("----Unique sorted receiver cities----");
        List<String> sortedCities = packages.stream()
                .map(CourierPackage::receiverCity)
                .distinct()
                .sorted()
                .toList();
        sortedCities.forEach(System.out::println);

    }
}
