package pl.kinga.kolekcjegeneryki.financesiterator;

import java.math.BigDecimal;

public record Transaction(String id, BigDecimal amount, String status) {

    public Transaction {
        if (id == null || id.isEmpty() ||
        amount == null || amount.compareTo(BigDecimal.ZERO) < 0 ||
        status == null || status.isEmpty()){
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
