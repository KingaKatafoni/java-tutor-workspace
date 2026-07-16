package pl.kinga.testowanie.lekcja7_9;

public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public double getBalance(String accountId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("Account ID is required");
        }

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }

        return account.balance();
    }

    public String withdraw(String accountId, double amount) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("Account ID is required");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }

        if (account.balance() < amount) {
            throw new InsufficientFundsException(accountId, amount, account.balance());
        }

        try {
            accountRepository.updateBalance(accountId, account.balance() - amount);
        } catch (RuntimeException ex) {
            throw new TransactionException("Failed to process withdrawal", ex);
        }

        return "Withdrawn " + amount + " from " + accountId;
    }

    public String transfer(String fromId, String toId, double amount){
        if (fromId == null || fromId.isEmpty() || toId == null || toId.isEmpty()){
            throw new IllegalArgumentException("Account ID is required");
        }

        if (fromId.equals(toId)){
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        Account fromAccount = accountRepository.findById(fromId);
        Account toAccount = accountRepository.findById(toId);

        if (fromAccount == null ){
            throw new AccountNotFoundException(fromId);
        }
        if (toAccount == null){
            throw new AccountNotFoundException(toId);
        }

        if (fromAccount.balance() < amount){
            throw new InsufficientFundsException(fromId, amount, fromAccount.balance());
        }

        accountRepository.updateBalance(fromId, fromAccount.balance() - amount);
        accountRepository.updateBalance(toId, toAccount.balance() + amount);

        return "Transferred " + amount + " from " + fromId + " to " + toId;
    }
}
