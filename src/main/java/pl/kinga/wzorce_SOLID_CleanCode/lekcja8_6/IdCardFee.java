package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

public class IdCardFee implements FeeStrategy {
    @Override
    public double calculate(int quantity, boolean urgentProcessing) {
        double pricePerEach;
        if (urgentProcessing) {
            pricePerEach = 30.0;
        } else {
            pricePerEach = 0.0;
        }
        return pricePerEach * quantity;
    }
}
