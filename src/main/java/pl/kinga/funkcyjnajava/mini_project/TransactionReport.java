package pl.kinga.funkcyjnajava.mini_project;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReport {
    public static void main(String[] args) {
        List<Account> accounts = List.of(
                new Account("ACC001", "Anna Kowalska", "PERSONAL", LocalDate.of(2020, 3, 15)),
                new Account("ACC002", "Jan Nowak", "BUSINESS", LocalDate.of(2019, 7, 1)),
                new Account("ACC003", "Maria Wiszniewska", "PERSONAL", LocalDate.of(2022, 1, 10)),
                new Account("ACC004", "Piotr Zielinski", "SAVINGS", LocalDate.of(2021, 6, 20)),
                new Account("ACC005", "Ewa Dabrowska", "BUSINESS", LocalDate.of(2023, 11, 5))
        );

        List<Transaction> transactions = List.of(
                new Transaction("TXN001", "ACC001", new BigDecimal("8500.00"), "DEPOSIT", "SALARY", LocalDate.of(2025, 4, 1), "Monthly salary"),
                new Transaction("TXN002", "ACC001", new BigDecimal("2200.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 4, 2), "Apartment rent"),
                new Transaction("TXN003", "ACC001", new BigDecimal("450.00"), "WITHDRAWAL", "GROCERIES", LocalDate.of(2025, 4, 5), "Weekly shopping"),
                new Transaction("TXN004", "ACC001", new BigDecimal("120.00"), "WITHDRAWAL", "ENTERTAINMENT", LocalDate.of(2025, 4, 8), "Cinema + dinner"),
                new Transaction("TXN005", "ACC001", new BigDecimal("350.00"), "WITHDRAWAL", "UTILITIES", LocalDate.of(2025, 4, 10), "Electricity + gas"),
                new Transaction("TXN006", "ACC001", new BigDecimal("1000.00"), "TRANSFER_OUT", "TRANSFER", LocalDate.of(2025, 4, 15), "Transfer to savings"),
                new Transaction("TXN007", "ACC002", new BigDecimal("25000.00"), "DEPOSIT", "OTHER", LocalDate.of(2025, 4, 1), "Client payment"),
                new Transaction("TXN008", "ACC002", new BigDecimal("4500.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 4, 3), "Office rent"),
                new Transaction("TXN009", "ACC002", new BigDecimal("1200.00"), "WITHDRAWAL", "UTILITIES", LocalDate.of(2025, 4, 7), "Internet + phone"),
                new Transaction("TXN010", "ACC002", new BigDecimal("8000.00"), "WITHDRAWAL", "OTHER", LocalDate.of(2025, 4, 12), "Contractor payment"),
                new Transaction("TXN011", "ACC002", new BigDecimal("15000.00"), "DEPOSIT", "OTHER", LocalDate.of(2025, 4, 20), "Client payment #2"),
                new Transaction("TXN012", "ACC003", new BigDecimal("6200.00"), "DEPOSIT", "SALARY", LocalDate.of(2025, 4, 1), "Monthly salary"),
                new Transaction("TXN013", "ACC003", new BigDecimal("1800.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 4, 2), "Room rent"),
                new Transaction("TXN014", "ACC003", new BigDecimal("280.00"), "WITHDRAWAL", "GROCERIES", LocalDate.of(2025, 4, 6), "Groceries"),
                new Transaction("TXN015", "ACC003", new BigDecimal("55.00"), "WITHDRAWAL", "ENTERTAINMENT", LocalDate.of(2025, 4, 9), "Spotify + Netflix"),
                new Transaction("TXN016", "ACC003", new BigDecimal("200.00"), "WITHDRAWAL", "UTILITIES", LocalDate.of(2025, 4, 11), "Water + heating"),
                new Transaction("TXN017", "ACC004", new BigDecimal("1000.00"), "TRANSFER_IN", "TRANSFER", LocalDate.of(2025, 4, 15), "From ACC001"),
                new Transaction("TXN018", "ACC004", new BigDecimal("5000.00"), "DEPOSIT", "INVESTMENT", LocalDate.of(2025, 4, 18), "Monthly investment"),
                new Transaction("TXN019", "ACC004", new BigDecimal("500.00"), "WITHDRAWAL", "OTHER", LocalDate.of(2025, 4, 25), "Emergency withdrawal"),
                new Transaction("TXN020", "ACC005", new BigDecimal("18000.00"), "DEPOSIT", "OTHER", LocalDate.of(2025, 4, 1), "Project milestone"),
                new Transaction("TXN021", "ACC005", new BigDecimal("3500.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 4, 3), "Coworking space"),
                new Transaction("TXN022", "ACC005", new BigDecimal("2200.00"), "WITHDRAWAL", "OTHER", LocalDate.of(2025, 4, 10), "Software licenses"),
                new Transaction("TXN023", "ACC005", new BigDecimal("950.00"), "WITHDRAWAL", "UTILITIES", LocalDate.of(2025, 4, 14), "Cloud hosting"),
                new Transaction("TXN024", "ACC005", new BigDecimal("12000.00"), "DEPOSIT", "OTHER", LocalDate.of(2025, 4, 22), "Project final payment"),
                new Transaction("TXN025", "ACC001", new BigDecimal("8500.00"), "DEPOSIT", "SALARY", LocalDate.of(2025, 5, 1), "Monthly salary May"),
                new Transaction("TXN026", "ACC001", new BigDecimal("2200.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 5, 2), "Apartment rent May"),
                new Transaction("TXN027", "ACC003", new BigDecimal("6200.00"), "DEPOSIT", "SALARY", LocalDate.of(2025, 5, 1), "Monthly salary May"),
                new Transaction("TXN028", "ACC003", new BigDecimal("1800.00"), "WITHDRAWAL", "RENT", LocalDate.of(2025, 5, 2), "Room rent May"),
                new Transaction("TXN029", "ACC002", new BigDecimal("32000.00"), "DEPOSIT", "OTHER", LocalDate.of(2025, 5, 5), "Major client payment"),
                new Transaction("TXN030", "ACC004", new BigDecimal("5000.00"), "DEPOSIT", "INVESTMENT", LocalDate.of(2025, 5, 18), "Monthly investment May")
        );

        System.out.println("### Section A — Basic metrics");
        System.out.println("1. ** Total amount of transactions**");
        long transactionCount = transactions.size();
        System.out.println(transactionCount);

        System.out.println("2. **Total amount(sum) of all transactions**");
        BigDecimal sumOfAllTransactions = transactions.stream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(sumOfAllTransactions + " PLN");

        System.out.println("3. **Average value of transactions**");
        BigDecimal averageValue = sumOfAllTransactions.divide(BigDecimal.valueOf(transactionCount), 2, RoundingMode.HALF_UP);
        System.out.println(averageValue + " PLN");

        System.out.println();
        System.out.println("### Section B — Withdrawal and deposit analysis");

        System.out.println("4. **Total amount of DEPOSIT and TRANSFER_IN transactions**");
        BigDecimal totalValueDepositAndTransferIn = transactions.stream()
                .filter(t -> t.type().equals("DEPOSIT") || t.type().equals("TRANSFER_IN"))
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(totalValueDepositAndTransferIn + " PLN");

        System.out.println("5. **Total amount of WITHDRAWAL and TRANSFER_OUT transactions**");
        BigDecimal totalValueWithdrawalAndTransferOut = transactions.stream()
                .filter(t -> t.type().equals("WITHDRAWAL") || t.type().equals("TRANSFER_OUT"))
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
        System.out.println(totalValueWithdrawalAndTransferOut + " PLN");

        System.out.println("6. **Balance**");
        BigDecimal balance = totalValueDepositAndTransferIn.subtract(totalValueWithdrawalAndTransferOut);
        System.out.println(balance + " PLN");

        System.out.println("7. **Top 3 the highest withdrawal**");
        List<String> top3HighestWithdrawal = transactions.stream()
                .filter(t -> t.type().equals("WITHDRAWAL") || t.type().equals("TRANSFER_OUT"))
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(3)
                .map(t -> t.amount() + " PLN " + t.description() + " " + t.date())
                .toList();
        top3HighestWithdrawal.forEach(System.out::println);

        System.out.println("8. **The biggest single transaction (whole object)**");
        transactions.stream()
                .max(Comparator.comparing(Transaction::amount))
                .ifPresent(System.out::println);

        System.out.println();
        System.out.println("### Section C — Analysis per account");

        System.out.println("9. **Total value of transactions per account**");
        Map<String, BigDecimal> totalValuePerAccount = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::accountId,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Transaction::amount,
                                BigDecimal::add
                        )
                ));
        totalValuePerAccount.forEach((k, v) -> System.out.println(k + " " + v + " PLN"));

        System.out.println("10. **Total amount of transactions per account**");
        Map<String, Long> totalAmountPerAccount = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::accountId,
                        Collectors.counting()
                ));

        totalAmountPerAccount.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("11. **Account with the highest total value of transactions**");
        totalValuePerAccount.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(p -> System.out.println(p.getKey() + " -> " + p.getValue() + " PLN"));

        System.out.println();
        System.out.println("### Section D — Analysis per category");

        System.out.println("12. **Total withdrawal per category**");
        Map<String, BigDecimal> totalWithdrawalPerCat = transactions.stream()
                .filter(t -> t.type().equals("WITHDRAWAL"))
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Transaction::amount,
                                BigDecimal::add
                        )
                ));

        totalWithdrawalPerCat.forEach((k, v) -> System.out.println(k + " " + v + " PLN"));

        System.out.println("13. **Category with the highest withdrawal**");
        totalWithdrawalPerCat.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(p -> System.out.println(p.getKey() + " -> " + p.getValue() + " PLN"));


        System.out.println("14. **Unique categories sorted alphabetically**");
        List<String> uniqueCategory = transactions.stream()
                .map(Transaction::category)
                .distinct()
                .sorted()
                .toList();
        uniqueCategory.forEach(System.out::println);

        System.out.println();
        System.out.println("### Section E — Time analysis");

        System.out.println("15. **Transactions from April vs from May (how many each month)**");
        Map<Month, Long> collected = transactions.stream()
                .collect(Collectors.groupingBy(
                        p -> p.date().getMonth(),
                        Collectors.counting()
                ));
        collected.forEach((k, v) -> System.out.println(k + " -> " + v + " transactions"));

        System.out.println("16. **Total value of transactions per month**");
        Map<Month, BigDecimal> totalValueOfTransactionsPerMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        p -> p.date().getMonth(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Transaction::amount,
                                BigDecimal::add
                        )
                ));
        totalValueOfTransactionsPerMonth.forEach((k, v) -> System.out.println(k + " -> " + v + " PLN"));

        System.out.println();
        System.out.println("### Section F — Searching");

        System.out.println("17. **Find transaction TXN015**");
        String transactionTXN015 = transactions.stream()
                .filter(t -> t.transactionId().equals("TXN015"))
                .findFirst()
                .map(Transaction::description)
                .orElse("No such transaction");
        System.out.println(transactionTXN015);

        System.out.println("18. **Find transaction TXN999**");
        transactions.stream()
                .filter(t -> t.transactionId().equals("TXN999"))
                .findFirst()
                .map(Transaction::description)
                .ifPresentOrElse(System.out::println, () -> System.out.println("No such transaction"));

        System.out.println("19. **Find account ACC003 owner**");
        String ownerOfACC003 = accounts.stream()
                .filter(a -> a.accountId().equals("ACC003"))
                .findFirst()
                .map(Account::ownerName)
                .orElse("No owner");
        System.out.println(ownerOfACC003);


        System.out.println("20. **Find account ACC099 owner**");
        String ownerOfACC099 = accounts.stream()
                .filter(a -> a.accountId().equals("ACC099"))
                .findFirst()
                .map(Account::ownerName)
                .orElse("No owner");
        System.out.println(ownerOfACC099);

        System.out.println();
        System.out.println("### Section G — Data merging of two lists");

        System.out.println("21. ** For each account print: accountId, ownerName, numberOfTransactions, total value of transactions**");
        accounts.forEach(account -> {
            List<Transaction> accountTransactions = transactions.stream()
                    .filter(t -> t.accountId().equals(account.accountId()))
                    .toList();
            long count = accountTransactions.size();
            BigDecimal total = accountTransactions.stream()
                    .map(Transaction::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            System.out.println(account.accountId() + " | " + account.ownerName() + " | " + count + " transactions | " + total + " PLN");
        });


    }
}
