package pl.kinga.oop.finances;

import java.math.BigDecimal;

public record PaymentConfirmation(String transactionId, String senderAccount, String receiverAccount, BigDecimal amount,
                                  String currency) {
    public PaymentConfirmation {
        if (transactionId == null || transactionId.isEmpty() || senderAccount == null || senderAccount.isEmpty() || receiverAccount == null || receiverAccount.isEmpty() || (amount == null ||  (amount.compareTo(BigDecimal.ZERO) <= 0) || currency == null || currency.isEmpty())) {
            throw new IllegalArgumentException("Inputs cannot be null or empty amount cannot be null or have negative value");
        }
    }

    public String format() {
        return transactionId + ": " +
                amount + " " +
                currency + " (" +
                senderAccount + " -> " +
                receiverAccount + ")";
    }

    public boolean isInternational() {
        return !currency.contains("PLN");
    }
}
