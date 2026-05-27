package pl.kinga.kolekcjegeneryki.financesiterator;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        TransactionFilter filter = new TransactionFilter();

        filter.addTransaction(new Transaction("TR/123/345", new BigDecimal("299.99"), "COMPLETED"));
        filter.addTransaction(new Transaction("TR/456/346", new BigDecimal("99.99"), "PENDING"));
        filter.addTransaction(new Transaction("TR/789/347", new BigDecimal("199.99"), "CANCELLED"));
        filter.addTransaction(new Transaction("TR/012/348", new BigDecimal("29.99"), "COMPLETED"));
        filter.addTransaction(new Transaction("TR/345/349", new BigDecimal("9.99"), "CANCELLED"));
        filter.addTransaction(new Transaction("TR/678/350", new BigDecimal("1299.99"), "PENDING"));
        filter.addTransaction(new Transaction("TR/901/351", new BigDecimal("5299.99"), "PENDING"));
        filter.addTransaction(new Transaction("TR/234/352", new BigDecimal("99.99"), "COMPLETED"));

        filter.printAll();
        System.out.println("--------------");
        filter.removeCancelled();
        filter.printAll();
        System.out.println("--------------");
        filter.removeBelowAmount(new BigDecimal("100.0"));
        filter.printAll();
    }
}
