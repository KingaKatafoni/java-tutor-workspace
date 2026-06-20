package pl.kinga.funkcyjnajava.lekcja5_6.publicsector;

import java.math.BigDecimal;

public record Officer(String id, String firstName, String lastName, String department, String rank, BigDecimal salary,
                      int yearsOfService) {
}
