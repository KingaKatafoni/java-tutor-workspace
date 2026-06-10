package pl.kinga.exceptionio.insurance;

public class InsuranceService {
    public void claimInsurance(String policyNumber) {
        if (policyNumber.equals("UNKNOWN")) {
            throw new PolicyNotFoundException(policyNumber);
        }

        if (policyNumber.equals("EXP-001")) {
            throw new PolicyExpiredException(policyNumber, "2025-12-31");
        }

        System.out.println("Claim accepted for policy: " + policyNumber);
    }
}
