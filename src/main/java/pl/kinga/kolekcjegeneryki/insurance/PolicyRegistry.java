package pl.kinga.kolekcjegeneryki.insurance;

import java.util.Map;
import java.util.TreeMap;

public class PolicyRegistry {
    private Map<String, PolicyInfo> policies;

    public PolicyRegistry() {
        this.policies = new TreeMap<>();
    }

    public void addPolicy(String policyNumber, PolicyInfo info) {
        PolicyInfo polInfo = policies.putIfAbsent(policyNumber, info);
        if (polInfo != null) {
            System.out.println("This policy number already exists!");
        }
    }

    public PolicyInfo getPolicy(String policyNumber) {
        return policies.get(policyNumber);
    }

    public void removePolicy(String policyNumber) {
        policies.remove(policyNumber);
    }

    public int getPolicyCount() {
        return policies.size();
    }

    public void printAllPolices() {
        System.out.println("---------Policies--------");
        for (Map.Entry<String, PolicyInfo> entry : policies.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
