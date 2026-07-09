package pl.kinga.testowanie.lekcja7_1;

import java.math.BigDecimal;

public class PensionCalculator {
    public double calculateMonthlyPension(double totalContributions, int retirementAge) {
        int expectedMonths;
        if (retirementAge == 60) {
            expectedMonths = 261;
        } else if (retirementAge == 65) {
            expectedMonths = 213;
        } else if (retirementAge == 67) {
            expectedMonths = 198;
        } else {
            throw new IllegalArgumentException("Unsupported retirement age: " + retirementAge);
        }

        if (totalContributions <= 0) {
            throw new IllegalArgumentException("Contributions must be positive");
        }
        double monthlyPension = totalContributions / expectedMonths;
        return (Math.round(monthlyPension * 100.0) / 100.0);
    }

    public String estimateCategory(double monthlyPension){
        if (monthlyPension < 0) {
            throw new IllegalArgumentException("Pension cannot be negative");
        } else if (monthlyPension < 1600){
            return "BELOW_MINIMUM";
        } else if (monthlyPension < 3000) {
            return "MINIMUM";
        } else if (monthlyPension < 5000) {
            return "AVERAGE";
        } else {
            return "ABOVE_AVERAGE";
        }
    }
}
