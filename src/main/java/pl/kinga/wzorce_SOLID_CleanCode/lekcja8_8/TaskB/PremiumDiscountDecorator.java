package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskB;

public class PremiumDiscountDecorator implements TransactionFee{
    private final TransactionFee wrapped;

    public PremiumDiscountDecorator(TransactionFee wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double calculate(double amount) {
        double result = wrapped.calculate(amount);
        return result * 0.9;
    }
}
