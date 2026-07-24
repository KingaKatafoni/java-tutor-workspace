package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

public class PassportFee implements FeeStrategy {

    @Override
    public double calculate(int quantity, boolean urgentProcessing) {
        double pricePerEach;
        if (urgentProcessing) {
            pricePerEach = 240.0;
        } else {
            pricePerEach = 140.0;
        }
        return pricePerEach * quantity;
    }
}
