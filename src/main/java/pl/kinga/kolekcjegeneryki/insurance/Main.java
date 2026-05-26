package pl.kinga.kolekcjegeneryki.insurance;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PolicyInfo policyInfo1 = new PolicyInfo("Jan Kowalski", "OC", 99.99);
        PolicyInfo policyInfo2 = new PolicyInfo("Maria Zdun", "AC", 299.99);
        PolicyInfo policyInfo3 = new PolicyInfo("Zbigniew Tracz", "Na zycie", 59.99);
        PolicyInfo policyInfo4 = new PolicyInfo("Anna Pol", "OC", 499.99);
        PolicyInfo policyInfo5 = new PolicyInfo("Marian Ant", "Na zycie ", 89.99);

        PolicyRegistry policyRegistry = new PolicyRegistry();

        for (Map.Entry<String, PolicyInfo> entry : Map.of("POL-003", policyInfo1, "POL-002", policyInfo2, "POL-004", policyInfo3, "POL-001", policyInfo4, "POL-005", policyInfo5).entrySet()) {
            policyRegistry.addPolicy(entry.getKey(), entry.getValue());
        }

        policyRegistry.printAllPolices();

        policyRegistry.addPolicy("POL-001", policyInfo2);
        policyRegistry.removePolicy("POL-005");
        policyRegistry.printAllPolices();


    }
}
