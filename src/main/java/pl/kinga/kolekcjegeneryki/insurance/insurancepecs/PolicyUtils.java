package pl.kinga.kolekcjegeneryki.insurance.insurancepecs;

import java.util.List;

public class PolicyUtils {
    public static void printPolicies(List<? extends Policy> policies) {
        for (Policy p : policies) {
            System.out.println(p);
        }
    }

    public static double sumPremiums(List<? extends Policy> policies) {
        double total = 0;
        for (Policy p : policies) {
            total += p.getPremium();
        }
        return total;
    }

    public static Policy findMostExpensive(List<? extends Policy> policies) {
        double max = 0;
        Policy mostExpensivePolicy = null;
        if (policies.isEmpty()) {
            return null;
        }
        for (Policy p : policies) {
            if (p.getPremium() > max) {
                max = p.getPremium();
                mostExpensivePolicy = p;
            }
        }
        return mostExpensivePolicy;
    }


}
