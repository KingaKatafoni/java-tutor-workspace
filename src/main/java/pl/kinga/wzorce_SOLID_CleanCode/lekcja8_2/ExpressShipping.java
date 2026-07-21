package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculate(Order order) {
        if (order.totalPrice() > 500) {
            return 10.0;
        }
        return 30.0;
    }
}
