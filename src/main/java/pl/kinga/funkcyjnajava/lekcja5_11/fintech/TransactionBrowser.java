package pl.kinga.funkcyjnajava.lekcja5_11.fintech;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class TransactionBrowser {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("TXN001", "PL10001", new BigDecimal("5000.00"), "DEPOSIT", "Salary", LocalDate.of(2025, 6, 1)),
                new Transaction("TXN002", "PL10001", new BigDecimal("120.50"), "WITHDRAWAL", "ATM cash", LocalDate.of(2025, 6, 2)),
                new Transaction("TXN003", "PL10002", new BigDecimal("3200.00"), "DEPOSIT", "Freelance payment", LocalDate.of(2025, 6, 2)),
                new Transaction("TXN004", "PL10001", new BigDecimal("89.99"), "WITHDRAWAL", "Netflix + Spotify", LocalDate.of(2025, 6, 3)),
                new Transaction("TXN005", "PL10002", new BigDecimal("450.00"), "TRANSFER_OUT", "Rent share", LocalDate.of(2025, 6, 4)),
                new Transaction("TXN006", "PL10003", new BigDecimal("450.00"), "TRANSFER_IN", "Rent share from PL10002", LocalDate.of(2025, 6, 4)),
                new Transaction("TXN007", "PL10001", new BigDecimal("2300.00"), "WITHDRAWAL", "Laptop repair", LocalDate.of(2025, 6, 5)),
                new Transaction("TXN008", "PL10003", new BigDecimal("7500.00"), "DEPOSIT", "Salary", LocalDate.of(2025, 6, 5)),
                new Transaction("TXN009", "PL10002", new BigDecimal("35.00"), "WITHDRAWAL", "Coffee shop", LocalDate.of(2025, 6, 6)),
                new Transaction("TXN010", "PL10001", new BigDecimal("1500.00"), "TRANSFER_OUT", "Savings", LocalDate.of(2025, 6, 7)),
                new Transaction("TXN011", "PL10003", new BigDecimal("200.00"), "WITHDRAWAL", "Groceries", LocalDate.of(2025, 6, 7)),
                new Transaction("TXN012", "PL10002", new BigDecimal("4100.00"), "DEPOSIT", "Contract payment", LocalDate.of(2025, 6, 8)),
                new Transaction("TXN013", "PL10001", new BigDecimal("65.00"), "WITHDRAWAL", "Pharmacy", LocalDate.of(2025, 6, 9)),
                new Transaction("TXN014", "PL10003", new BigDecimal("1800.00"), "TRANSFER_OUT", "Tax payment", LocalDate.of(2025, 6, 10)),
                new Transaction("TXN015", "PL10002", new BigDecimal("99.99"), "WITHDRAWAL", "Book order", LocalDate.of(2025, 6, 10))
        );

        System.out.println("----1# 5 the newest transactions----");
        List<Transaction> newest5Transactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::date).reversed())
                .limit(5)
                .toList();
        newest5Transactions.forEach(System.out::println);

        System.out.println("----2# 3 the biggest withdrawal----");
        transactions.stream()
                .filter(t -> t.type().equals("WITHDRAWAL"))
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("----3# All transaction without 3 the oldest----");
        List<Transaction> theOldestTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .skip(3)
                .toList();

        theOldestTransactions.forEach(System.out::println);

        System.out.println("----4# Pagination 4 transactions per page----");
        int pageSize = 4;
        List<Transaction> page0 = transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .skip(0)
                .limit(pageSize)
                .toList();
        List<Transaction> page1 = transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .skip(pageSize)
                .limit(pageSize)
                .toList();
        List<Transaction> page2 = transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .skip(2 * pageSize)
                .limit(pageSize)
                .toList();

        System.out.println("--- Page 0 ---");
        page0.forEach(System.out::println);
        System.out.println("--- Page 1 ---");
        page1.forEach(System.out::println);
        System.out.println("--- Page 2 ---");
        page2.forEach(System.out::println);

        System.out.println("----5# debugging----");
        List<String> debugging = transactions.stream()
                .filter(t -> t.accountNumber().equals("PL10001"))
                .peek(s -> System.out.println("Processing: " + s.transactionId()))
                .filter(p -> p.amount().compareTo(new BigDecimal("100.00")) > 0)
                .map(Transaction::description)
                .toList();
        debugging.forEach(System.out::println);

        System.out.println("----6# the biggest transaction----");
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(1)
                .forEach(System.out::println);
    }
}
