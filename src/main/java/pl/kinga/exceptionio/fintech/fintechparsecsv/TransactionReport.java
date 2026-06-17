package pl.kinga.exceptionio.fintech.fintechparsecsv;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionReport {
    public static Transaction parseLine(String line, int lineNumber) {
        String[] col = line.split(",");
        if (col.length < 8) {
            System.out.println("Skipping line: " + lineNumber);
            return null;
        }

        if (col[3].isEmpty()) {
            col[3] = "N/A";
        }

        try {
            BigDecimal amount = new BigDecimal(col[4]);
            return new Transaction(col[0], col[1], col[2], col[3], amount, col[5], col[6], col[7]);
        } catch (NumberFormatException e) {
            System.out.println("Skipping line " + lineNumber + ": " + e.getMessage());
            return null;
        }

    }

    public static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            return new ArrayList<>();
        }

        for (int i = 1; i < lines.size(); i++) {
            Transaction transaction = parseLine(lines.get(i), i);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    public static void generateReport(List<Transaction> transactions, String outputPath) {
        List<String> outputLines = new ArrayList<>();
        int transferTransactionCount = 0;
        int paymentTransactionCount = 0;
        int withdrawalTransactionCount = 0;

        BigDecimal totalTransferAmount = BigDecimal.ZERO;
        BigDecimal totalPaymentAmount = BigDecimal.ZERO;
        BigDecimal totalWithdrawalAmount = BigDecimal.ZERO;

        BigDecimal maxAmount = BigDecimal.ZERO;
        Transaction best = null;

        outputLines.add("=== Transaction Report ===");
        for (Transaction transaction : transactions) {

            if (transaction.currency().equals("PLN")) {
                if (transaction.type().equals("TRANSFER")) {
                    transferTransactionCount++;
                    totalTransferAmount = totalTransferAmount.add(transaction.amount());
                } else if (transaction.type().equals("PAYMENT")) {
                    paymentTransactionCount++;
                    totalPaymentAmount = totalPaymentAmount.add(transaction.amount());
                } else if (transaction.type().equals("WITHDRAWAL")) {
                    withdrawalTransactionCount++;
                    totalWithdrawalAmount = totalWithdrawalAmount.add(transaction.amount());
                }
            }

            if (transaction.amount().compareTo(maxAmount) >= 0) {
                maxAmount = transaction.amount();
                best = transaction;
            }
        }

        outputLines.add("TRANSFER: " + transferTransactionCount + " transactions, total: " + totalTransferAmount + " PLN");
        outputLines.add("PAYMENT: " + paymentTransactionCount + " transactions, total: " + totalPaymentAmount + " PLN");
        outputLines.add("WITHDRAWAL: " + withdrawalTransactionCount + " transactions, total: " + totalWithdrawalAmount + " PLN");
        outputLines.add("---");
        BigDecimal grandTotal = totalPaymentAmount.add(totalTransferAmount.add(totalWithdrawalAmount));
        outputLines.add("Grand total: " + grandTotal);
        if (best != null) {
            outputLines.add("Largest transaction: " + best.id() + " - " + best.amount() + " " + best.currency() + " (" + best.description() + ")");
        }

        Path dir = Path.of(outputPath);
        try {
            Files.createDirectories(dir);
            Files.write(dir.resolve("transaction_report.txt"), outputLines);
        } catch (IOException e) {
            System.out.println("Cannot write report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = loadTransactions("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/09_parsowanie_csv/dane/transactions.csv");
        generateReport(transactions, "/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/09_parsowanie_csv/output");

        List<String> lines = List.of();
        try {
            lines = Files.readAllLines(Path.of("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/09_parsowanie_csv/output/transaction_report.txt"));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }

        for (String line : lines) {
            System.out.println(line);
        }

    }
}
