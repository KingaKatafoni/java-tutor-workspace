package pl.kinga.funkcyjnajava.lekcja5_4.publicsector;

import java.math.BigDecimal;

public record TaxRecord(String nip, String taxpayerName, String city, BigDecimal annualIncome, BigDecimal taxPaid,
                        String status) {
}
