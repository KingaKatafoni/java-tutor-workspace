package pl.kinga.exceptionio.ecommerce;

public class Main {
    public static void main (String[] args){
        try(PaymentGatewayConnection paymentGatewayConnection = new PaymentGatewayConnection("GATE-001");
        TransactionLog transactionLog = new TransactionLog("LOG-001")){
            paymentGatewayConnection.processPayment("ORD-001", 1000.50);
            transactionLog.log("Payment 1000.50 PLN for ORD-001");
        }

        try(PaymentGatewayConnection paymentGatewayConnection = new PaymentGatewayConnection("GATE-002");
        TransactionLog transactionLog = new TransactionLog("LOG-002")){
            paymentGatewayConnection.processPayment("ORD-002", -30.0);
        } catch (IllegalArgumentException e){
            System.out.println("Invalid value");
        }
    }
}
