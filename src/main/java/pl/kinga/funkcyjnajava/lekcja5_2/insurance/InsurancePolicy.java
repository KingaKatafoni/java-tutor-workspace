package pl.kinga.funkcyjnajava.lekcja5_2.insurance;

import java.math.BigDecimal;

public record InsurancePolicy(String policyNumber, String holderName, String type, BigDecimal premium,
                              boolean isActive) {
}
