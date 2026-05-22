package pl.kinga.kolekcjegeneryki.finances2;

import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private List<String> transaction;

    public TransactionLog() {
        this.transaction = new ArrayList<>();
    }

    public void addTransaction(String description) {
        transaction.add(description);
    }

    public void addUrgentTransaction(String description) {
        transaction.add(0, description);
    }

    public String getTransaction(int index) {
        return transaction.get(index);
    }

    public String getLastTransaction() {
        if (transaction.isEmpty()) {
            return null;
        } else {
            return transaction.get(transaction.size() - 1);
        }
    }

    public void removeOldestTransaction() {
        if (transaction.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            transaction.remove(0);
        }
    }

    public void removeNewestTransaction() {
        if (transaction.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            transaction.remove(transaction.size() - 1);
        }
    }

    public boolean containsTransaction(String description) {
        return transaction.contains(description);
    }

    public Integer getTransactionCount() {
        return transaction.size();
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "transaction=" + transaction +
                '}';
    }
}
