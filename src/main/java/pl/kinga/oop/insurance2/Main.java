package pl.kinga.oop.insurance2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> risks = new ArrayList<>(List.of("fire", "flood", "theft"));
        InsurancePolicy insurancePolicy = new InsurancePolicy("POL-2026-00142", "Jan Kowalski", risks);
        System.out.println(insurancePolicy);
        risks.add("earthquake");
        System.out.println(insurancePolicy);
        System.out.println("Is \"earthquake\" covered: " + insurancePolicy.isRiskCovered("earthquake"));
        System.out.println("Risks count: " + insurancePolicy.getCoveredRiskCount());
    }
}
