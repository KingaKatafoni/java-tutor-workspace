package pl.kinga.kolekcjegeneryki.financesiterator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionFilter {
    private final List<Transaction> transactions;

    public TransactionFilter() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t){
        transactions.add(t);
    }

    public void removeCancelled() {
        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()){
            Transaction transaction = it.next();
            if (transaction.status().equals("CANCELLED")){
                it.remove();
            }
        }
    }

    public void removeBelowAmount (BigDecimal minAmount) {
        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()){
            Transaction transaction = it.next();
            if(transaction.amount().compareTo(minAmount) < 0) {
                it.remove();
            }
        }
    }

    public void printAll() {
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
}
