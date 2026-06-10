package pl.kinga.exceptionio.fintech.fitechgoodpractices;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, Double> accounts;

    public AccountService() {
        accounts = new HashMap<>();
        accounts.put("ACC-001", 5000.0);
        accounts.put("ACC-002", 200.0);
    }

    public void withdraw(String accountId, double amount) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account Id " + accountId + " does not exist!");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (accounts.get(accountId) < amount) {
            throw new InsufficientFundsException(accountId, amount, accounts.get(accountId));
        }
        double val = accounts.get(accountId) - amount;
        accounts.put(accountId, val);
        System.out.println("Withdrawn " + amount + " from " + accountId + ". New balance: " + val);

    }
}
