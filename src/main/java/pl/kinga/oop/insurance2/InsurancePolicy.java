package pl.kinga.oop.insurance2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InsurancePolicy {
    private final String policyNumber;
    private final String holderName;
    private final List<String> coveredRisks;

    public InsurancePolicy(String policyNumber, String holderName, List<String> coveredRisks) {
        if (policyNumber == null || policyNumber.isEmpty() || holderName == null || holderName.isEmpty() || coveredRisks == null || coveredRisks.isEmpty()) {
            throw new IllegalArgumentException("No null and empty values allowed!");
        }
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.coveredRisks = new ArrayList<>(coveredRisks);
    }

    public String getPolicyNumber(){
        return policyNumber;
    }

    public String getHolderName(){
        return holderName;
    }

    public List<String> getCoveredRisks(){
        return Collections.unmodifiableList(coveredRisks);
    }

    public boolean isRiskCovered(String risk){
        return coveredRisks.contains(risk);
    }

    public int getCoveredRiskCount(){
        return coveredRisks.size();
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                ", risks=" + getCoveredRiskCount() +
                '}';
    }
}
