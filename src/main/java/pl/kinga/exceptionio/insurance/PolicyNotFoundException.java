package pl.kinga.exceptionio.insurance;

public class PolicyNotFoundException extends RuntimeException {
    private final String policyNumber;

    public PolicyNotFoundException(String policyNumber) {
        super("Policy not found: " + policyNumber);
        this.policyNumber = policyNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
}
