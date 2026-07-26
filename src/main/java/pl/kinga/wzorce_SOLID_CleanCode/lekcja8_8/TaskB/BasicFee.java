package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskB;

public class BasicFee implements TransactionFee{
    @Override
    public double calculate(double amount) {
        return amount;
    }
}
