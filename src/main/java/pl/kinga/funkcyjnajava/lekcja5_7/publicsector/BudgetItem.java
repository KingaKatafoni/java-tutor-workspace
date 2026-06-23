package pl.kinga.funkcyjnajava.lekcja5_7.publicsector;

import java.math.BigDecimal;

public record BudgetItem(String id, String category, String department, BigDecimal amount, String quarter,
                         boolean approved) {
}
