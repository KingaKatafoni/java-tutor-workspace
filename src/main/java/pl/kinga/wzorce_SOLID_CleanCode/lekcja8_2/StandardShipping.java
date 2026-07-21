package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public class StandardShipping implements ShippingStrategy {

    @Override
    public double calculate(Order order) {
        if (order.totalPrice() > 200) {
            return 0;
        }
        return 15.0;
    }
}
