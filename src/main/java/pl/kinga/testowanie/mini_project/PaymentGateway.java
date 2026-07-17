package pl.kinga.testowanie.mini_project;

public interface PaymentGateway {
    PaymentResult charge(String customerId, double amount);
}
