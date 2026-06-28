package pl.kinga.funkcyjnajava.mini_project;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(String transactionId, String accountId, BigDecimal amount, String type, String category,
                          LocalDate date, String description) {
}
