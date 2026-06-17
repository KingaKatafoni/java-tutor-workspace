package pl.kinga.exceptionio.publicsector.publicsectorparsecsv;

import java.math.BigDecimal;

public record Taxpayer(String nip, String name, String address,
                       String taxOffice, BigDecimal annualIncome, BigDecimal taxPaid,
                       String status) {
}
