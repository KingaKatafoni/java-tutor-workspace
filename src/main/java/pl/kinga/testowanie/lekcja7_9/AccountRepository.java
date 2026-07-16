package pl.kinga.testowanie.lekcja7_9;

public interface AccountRepository {
    Account findById(String accountId);

    void updateBalance(String accountId, double newBalance);
}
