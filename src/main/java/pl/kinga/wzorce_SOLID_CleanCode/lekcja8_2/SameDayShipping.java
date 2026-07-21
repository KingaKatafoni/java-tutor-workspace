package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public class SameDayShipping implements ShippingStrategy {
    @Override
    public double calculate(Order order) {
        return 50.0;
    }
}
