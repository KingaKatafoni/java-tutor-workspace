package pl.kinga.kolekcjegeneryki.insurance.insurancepecs;

public class Policy {
    private String policyNumber;
    private double premium;

    public Policy(String policyNumber, double premium) {
        if (policyNumber == null || policyNumber.isEmpty() || premium < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }

        this.policyNumber = policyNumber;
        this.premium = premium;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", premium=" + premium +
                '}';
    }

}
