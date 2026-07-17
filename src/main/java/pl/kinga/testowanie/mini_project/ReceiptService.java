package pl.kinga.testowanie.mini_project;

public interface ReceiptService {
    void sendReceipt(String email, String transactionId, double amount);
}
