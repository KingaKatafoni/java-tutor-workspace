package pl.kinga.funkcyjnajava.lekcja5_4.publicsector;

import java.math.BigDecimal;
import java.util.List;

public class TaxAnalyzer {
    public static void main(String[] args) {
        List<TaxRecord> records = List.of(
                new TaxRecord("726123", "Kowalska Anna", "Warszawa", new BigDecimal("85000"), new BigDecimal("14450"),
                        "ACTIVE"),
                new TaxRecord("528123", "Wisniewski Jan", "Krakow", new BigDecimal("62000"), new BigDecimal("10540"),
                        "SUSPENDED"),
                new TaxRecord("839123", "Lewandowska Maja", "Gdansk", new BigDecimal("45000"), new BigDecimal("7650"),
                        "ACTIVE"),
                new TaxRecord("976123", "Kazimierczak Zygmunt", "Poznan", new BigDecimal("120000"), new BigDecimal("16650"),
                        "CLOSED"),
                new TaxRecord("453123", "Moniuszko Sabina", "Berlin", new BigDecimal("89000"), new BigDecimal("3650"),
                        "ACTIVE"),
                new TaxRecord("246123", "Zebrzydowska Kalina", "Konin", new BigDecimal("5000"), new BigDecimal("650"),
                        "CLOSED"),
                new TaxRecord("874123", "Podlaski Adam", "Gdansk", new BigDecimal("145000"), new BigDecimal("17650"),
                        "SUSPENDED"),
                new TaxRecord("113234", "Kaminski Piotr", "Poznan", new BigDecimal("150000"), new BigDecimal("28500"),
                        "ACTIVE"));

        System.out.println("------Active taxpayers------");
        List<TaxRecord> activeTaxpayers = records.stream()
                .filter(t -> t.status().equals("ACTIVE"))
                .toList();
        activeTaxpayers.forEach(System.out::println);

        System.out.println("------Taxpayers Names-------");
        List<String> taxpayersNames = records.stream()
                .map(TaxRecord::taxpayerName)
                .toList();
        taxpayersNames.forEach(System.out::println);

        System.out.println("----Suspended taxpayers------");
        long suspendedTaxpayers = records.stream()
                .filter(t -> t.status().equals("SUSPENDED"))
                .count();
        System.out.println("Suspended taxpayers: " + suspendedTaxpayers);
        ;

        System.out.println("-----Unique Cities-----");
        List<String> uniqueCities = records.stream()
                .map(TaxRecord::city)
                .distinct()
                .toList();
        uniqueCities.forEach(System.out::println);

        System.out.println("------Taxpayers------");
        records.stream()
                .map(c -> "[" + c.status() + "] " + c.taxpayerName() + " - " + c.city())
                .forEach(System.out::println);

        System.out.println("----Active + Income > 100 000------");
        List<TaxRecord> activeAndRich = records.stream()
                .filter(c -> (c.status().equals("ACTIVE")) && (c.annualIncome().compareTo(new BigDecimal("100000"))) > 0)
                .toList();
        activeAndRich.forEach(System.out::println);

    }
}
