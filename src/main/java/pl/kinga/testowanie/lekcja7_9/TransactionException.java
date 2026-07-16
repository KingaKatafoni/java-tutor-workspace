package pl.kinga.testowanie.lekcja7_9;

public class TransactionException extends RuntimeException {
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
