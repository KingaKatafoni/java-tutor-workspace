package pl.kinga.testowanie.mini_project;

public class CustomerNotFoundException extends RuntimeException{
    private final String customerId;

    public CustomerNotFoundException(String customerId){
        super("Customer not found: " + customerId);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}

