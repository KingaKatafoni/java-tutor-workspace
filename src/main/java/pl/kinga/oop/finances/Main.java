package pl.kinga.oop.finances;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PaymentConfirmation paymentConfirmationOne = new PaymentConfirmation("TRX-2026-05-00412", "PL61109010140000071219812874", "PL62309010140000071219812874", new BigDecimal("1500.00"), "PLN");
        PaymentConfirmation paymentConfirmationTwo = new PaymentConfirmation("TRX-2026-05-00536", "PL56409010140000071219812898", "PL62309010140000071219816543", new BigDecimal("500.00"), "EUR");

        System.out.println(paymentConfirmationOne);
        System.out.println(paymentConfirmationTwo);

        System.out.println("----------------------------");
        System.out.println(paymentConfirmationOne.format());
        System.out.println(paymentConfirmationTwo.format());

        System.out.println("----------------------------");
        System.out.println("Is " + paymentConfirmationOne.transactionId() + " international? : " + paymentConfirmationOne.isInternational() + " (amount: " + paymentConfirmationOne.amount() + " " + paymentConfirmationOne.currency() + ")");
        System.out.println("Is " + paymentConfirmationTwo.transactionId() + " international? : " + paymentConfirmationTwo.isInternational() + " (amount: " + paymentConfirmationTwo.amount() + " " + paymentConfirmationTwo.currency() + ")");

        PaymentConfirmation paymentConfirmationThree = new PaymentConfirmation("TRX-2026-05-00536", "PL56409010140000071219812898", "PL62309010140000071219816543", new BigDecimal("500.00"), "EUR");
        System.out.println("Is transaction " + paymentConfirmationTwo.transactionId() + " equal to " + paymentConfirmationThree.transactionId() + ": " + paymentConfirmationTwo.equals(paymentConfirmationThree));
    }
}
