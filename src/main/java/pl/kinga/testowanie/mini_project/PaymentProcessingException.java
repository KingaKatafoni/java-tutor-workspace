package pl.kinga.testowanie.mini_project;

public class PaymentProcessingException extends RuntimeException {
    private final String transactionId;

    public PaymentProcessingException(String message, String transactionId, Throwable cause) {
        super(message, cause);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
