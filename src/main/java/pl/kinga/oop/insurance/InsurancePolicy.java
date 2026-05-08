package pl.kinga.oop.insurance;

import java.util.Objects;

public class InsurancePolicy {
    private String policyNumber;
    private String holderName;

    public InsurancePolicy(String policyNumber, String holderName) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        InsurancePolicy other = (InsurancePolicy) obj;
        return policyNumber.equals(other.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }
}
