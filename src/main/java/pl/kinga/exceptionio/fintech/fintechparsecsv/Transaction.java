package pl.kinga.exceptionio.fintech.fintechparsecsv;

import java.math.BigDecimal;

public record Transaction(String id, String date, String senderAccount, String receiverAccount, BigDecimal amount, String currency, String type, String description) {
}
