package pl.kinga.funkcyjnajava.lekcja5_5.publicsector;

import java.math.BigDecimal;

public record Employee(String employeeId, String firstName, String lastName, String department, BigDecimal salary,
                       int yearsOfService, String position) {
}
