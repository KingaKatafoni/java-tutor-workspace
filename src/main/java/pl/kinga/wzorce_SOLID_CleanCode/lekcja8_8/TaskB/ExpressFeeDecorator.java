package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskB;

public class ExpressFeeDecorator implements TransactionFee{
    private final TransactionFee wrapped;

    public ExpressFeeDecorator(TransactionFee wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double calculate(double amount) {
        double result = wrapped.calculate(amount);
        return result + 20.0;
    }
}
