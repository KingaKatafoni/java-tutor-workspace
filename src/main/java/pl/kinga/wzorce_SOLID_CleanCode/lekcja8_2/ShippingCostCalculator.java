package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public class ShippingCostCalculator {
    private final ShippingStrategy shippingStrategy;


    public ShippingCostCalculator(ShippingStrategy shippingStrategy) {
        if (shippingStrategy == null) {
            throw new IllegalArgumentException("Shipping strategy is required");
        }
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateShippingCost(Order order) {
        return shippingStrategy.calculate(order);
    }
}


