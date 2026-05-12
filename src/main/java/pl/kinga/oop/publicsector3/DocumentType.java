package pl.kinga.oop.publicsector3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum DocumentType {
    ID_CARD("Dowód osobisty", 10, new BigDecimal("0.0")),
    PASSPORT("Paszport", 10, new BigDecimal("140.0")),
    DRIVING_LICENSE("Prawo jazdy", 15, new BigDecimal("100.50"));

    private final String description;
    private final int validityYears;
    private final BigDecimal fee;


    DocumentType(String description, int validityYears, BigDecimal fee) {

        this.description = description;
        this.validityYears = validityYears;
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public int getValidityYears() {
        return validityYears;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public boolean isFree() {
        return (fee.compareTo(BigDecimal.ZERO) == 0);
    }

    @Override
    public String toString() {
        return name() + "(" +
                description + ", " +
                validityYears + " years, " +
                fee.setScale(2, RoundingMode.HALF_UP) + " PLN)";
    }
}
