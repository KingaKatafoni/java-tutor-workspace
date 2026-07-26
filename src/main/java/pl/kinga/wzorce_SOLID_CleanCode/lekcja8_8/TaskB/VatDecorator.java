package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskB;

public class VatDecorator implements TransactionFee {
    private final TransactionFee wrapped;

    public VatDecorator(TransactionFee wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double calculate(double amount) {
        double result = wrapped.calculate(amount);
        return result * 1.23;
    }
}
