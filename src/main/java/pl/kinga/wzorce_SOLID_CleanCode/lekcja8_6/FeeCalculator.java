package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

public class FeeCalculator {
    private final FeeStrategy feeStrategy;

    public FeeCalculator(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public double calculateTotalFee(int quantity, boolean urgentProcessing) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        return feeStrategy.calculate(quantity, urgentProcessing);
    }
}
