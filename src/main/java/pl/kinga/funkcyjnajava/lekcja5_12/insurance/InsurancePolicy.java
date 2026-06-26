package pl.kinga.funkcyjnajava.lekcja5_12.insurance;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InsurancePolicy(String policyNumber, String holderName, String type, BigDecimal premium, LocalDate expiryDate, String agentName) {
}
