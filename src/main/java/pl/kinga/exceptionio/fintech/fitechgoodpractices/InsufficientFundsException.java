package pl.kinga.exceptionio.fintech.fitechgoodpractices;

public class InsufficientFundsException extends RuntimeException{
    private final String accountId;
    private final double requested;
    private final double balance;

    public InsufficientFundsException(String accountId, double requested, double balance){
        super("Account " + accountId + ": requested " + requested + ", balance" + balance);
        this.accountId = accountId;
        this.requested = requested;
        this.balance = balance;
    }

    public String getAccountId(){
        return accountId;
    }

    public double getRequested(){
        return requested;
    }

    public double getBalance() {
        return balance;
    }
}
