package pl.kinga.kolekcjegeneryki.insurance.insurancepecs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<LifePolicy> lifePolicies = new ArrayList<>(
                List.of(
                        new LifePolicy("LIFE/098766/001", 234.99, 12),
                        new LifePolicy("LIFE/83652/002", 567.98, 25),
                        new LifePolicy("LIFE/8311/003", 8765.00, 50)));

        List<CarPolicy> carPolicies = new ArrayList<>(
                List.of(
                        new CarPolicy("CAR/7653/001", 3456.99, "BMW"),
                        new CarPolicy("CAR/1425/002", 765.75, "Audi"),
                        new CarPolicy("CAR/876/003", 1234.99, "Mercedes")));

        PolicyUtils.printPolicies(lifePolicies);
        PolicyUtils.printPolicies(carPolicies);

        System.out.println("Total sum for carPolicies " + PolicyUtils.sumPremiums(carPolicies));
        System.out.println("Total sum for lifePolicies " + PolicyUtils.sumPremiums(lifePolicies));

        System.out.println("The most expensive life policy: " + PolicyUtils.findMostExpensive(lifePolicies));
        System.out.println("The most expensive car policy: " + PolicyUtils.findMostExpensive(carPolicies));
    }
}
