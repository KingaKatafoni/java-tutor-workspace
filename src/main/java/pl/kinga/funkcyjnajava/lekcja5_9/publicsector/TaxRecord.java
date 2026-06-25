package pl.kinga.funkcyjnajava.lekcja5_9.publicsector;

import java.math.BigDecimal;

public record TaxRecord(String taxpayerId, String fullName, String city, String taxType, BigDecimal amount, int year,
                        boolean paid) {
}
