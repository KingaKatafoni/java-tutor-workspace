package pl.kinga.testowanie.mini_project;

public record PaymentResult(String transactionId, String customerId, double amount, PaymentStatus status, String message) {
}
