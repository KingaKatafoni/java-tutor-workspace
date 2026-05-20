package pl.kinga.kolekcjegeneryki.finances;

import java.util.*;

public class BankDataManager {
    private List<String> transactionHistory;
    private Set<String> activeAccountNumber;
    private Map<String, String> accountHolders;

    public BankDataManager() {
        this.transactionHistory = new ArrayList<>();
        this.activeAccountNumber = new HashSet<>();
        this.accountHolders = new HashMap<>();
    }

    public void addTransaction(String description) {
        transactionHistory.add(description);
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public void addAccount(String accountNumber, String holderName) {
        if (activeAccountNumber.contains(accountNumber)) {
            System.out.println("You cannot add this account, account already exists!");
            return;
        }
        activeAccountNumber.add(accountNumber);
        accountHolders.put(accountNumber, holderName);
    }

    public String getAccountHolder(String accountNumber) {
        return accountHolders.get(accountNumber);
    }

    public int getAccountCount() {
        return activeAccountNumber.size();
    }

    public boolean isAccountActive(String accountNumber) {
        return activeAccountNumber.contains(accountNumber);
    }

    public int getTransactionCount() {
        return transactionHistory.size();
    }


}
