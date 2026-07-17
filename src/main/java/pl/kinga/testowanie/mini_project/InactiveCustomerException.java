package pl.kinga.testowanie.mini_project;

public class InactiveCustomerException extends RuntimeException{
    private final String customerId;

    public InactiveCustomerException(String customerId){
        super("Customer account is inactive: " + customerId);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
