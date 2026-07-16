package pl.kinga.testowanie.lekcja7_9;

public class InsufficientFundsException extends RuntimeException {
    private final String accountId;
    private final double requested;
    private final double available;

    public InsufficientFundsException(String accountId, double requested, double available) {
        super("Insufficient funds on account " + accountId + ": requested " + requested + ", available " + available);
        this.accountId = accountId;
        this.requested = requested;
        this.available = available;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getRequested() {
        return requested;
    }

    public double getAvailable() {
        return available;
    }
}
