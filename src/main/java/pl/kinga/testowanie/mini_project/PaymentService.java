package pl.kinga.testowanie.mini_project;

public class PaymentService {
    private final CustomerRepository customerRepository;
    private final PaymentGateway paymentGateway;
    private final ReceiptService receiptService;

    private static final double MIN_AMOUNT = 1.0;
    private static final double MAX_AMOUNT = 50000.0;

    public PaymentService(CustomerRepository customerRepository, PaymentGateway paymentGateway, ReceiptService receiptService) {
        this.customerRepository = customerRepository;
        this.paymentGateway = paymentGateway;
        this.receiptService = receiptService;
    }

    public PaymentResult processPayment(String customerId, double amount) {
        // validation
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("Amount must be at least " + MIN_AMOUNT);
        }

        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("Amount cannot exceed " + MAX_AMOUNT);
        }

        //client verification
        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }

        if (!customer.active()) {
            throw new InactiveCustomerException(customerId);
        }

        //payment processing
        PaymentResult paymentResult;
        try {
            paymentResult = paymentGateway.charge(customerId, amount);
        } catch (RuntimeException e) {
            throw new PaymentProcessingException("Payment gateway error", "TXN-FAILED", e);
        }

        if (paymentResult.status() == PaymentStatus.APPROVED) {
            receiptService.sendReceipt(customer.email(), paymentResult.transactionId(), amount);
        }

        return paymentResult;

    }

    public boolean validatePaymentAmount(double amount){
        if (amount < MIN_AMOUNT){
            return false;
        } else if (amount > MAX_AMOUNT){
            return false;
        } else {
            return true;
        }
    }

}

