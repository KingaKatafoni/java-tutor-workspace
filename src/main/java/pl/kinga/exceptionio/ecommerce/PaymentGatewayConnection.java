package pl.kinga.exceptionio.ecommerce;

public class PaymentGatewayConnection implements AutoCloseable {
    private String gatewayName;
    private boolean connected = false;

    public PaymentGatewayConnection(String gatewayName) {
        this.gatewayName = gatewayName;
        connected = true;
        System.out.println("Connected to gateway: " + gatewayName);
    }

    public void processPayment(String orderId, double amount) {
        if (!connected) {
            throw new IllegalStateException("Not connected to gateway");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
        System.out.println("Payment processed: " + amount + " PLN for order " + orderId);
    }


    @Override
    public void close(){
        connected = false;
        System.out.println("Gateway " + gatewayName + " connection closed");
    }
}
