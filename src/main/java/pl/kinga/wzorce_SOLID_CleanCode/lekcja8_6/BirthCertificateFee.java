package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

public class BirthCertificateFee implements FeeStrategy {

    @Override
    public double calculate(int quantity, boolean urgentProcessing) {
        double pricePerEach;
        if (urgentProcessing) {
            pricePerEach = (22.0 * 1.5);
        } else {
            pricePerEach = 22.0;
        }

        return pricePerEach * quantity;
    }
}
