package pl.kinga.funkcyjnajava.lekcja5_11.fintech;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(String transactionId, String accountNumber, BigDecimal amount, String type,
                          String description, LocalDate date) {
}
