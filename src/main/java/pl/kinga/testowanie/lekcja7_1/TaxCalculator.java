package pl.kinga.testowanie.lekcja7_1;

public class TaxCalculator {
    public double calculateIncomeTax(double income) {
        if (income <= 0) {
            throw new IllegalArgumentException("Income must be positive");
        }

        if (income <= 120000) {
            return income * 0.12;
        }
        return 120000 * 0.12 + (income - 120000) * 0.32;
    }
}
