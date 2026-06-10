package pl.kinga.exceptionio.insurance;

public class PolicyExpiredException extends RuntimeException{
    private final String policyNumber;
    private final String expirationDate;

    public PolicyExpiredException(String policyNumber, String expirationDate){
        super("Policy " + policyNumber + " expired on " + expirationDate);
        this.policyNumber = policyNumber;
        this.expirationDate = expirationDate;
    }

    public String getPolicyNumber(){
        return policyNumber;
    }

    public String getExpirationDate(){
        return expirationDate;
    }
}
