package pl.kinga.funkcyjnajava.lekcja5_2.insurance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PolicyService {
    public static List<InsurancePolicy> filterPolices(List<InsurancePolicy> polices, Predicate<InsurancePolicy> condition) {
        List<InsurancePolicy> result = new ArrayList<>();
        for (InsurancePolicy policy : polices) {
            if (condition.test(policy)) {
                result.add(policy);
            }
        }
        return result;
    }

    public static List<String> extractData(List<InsurancePolicy> polices, Function<InsurancePolicy, String> extractor) {
        List<String> results = new ArrayList<>();
        for (InsurancePolicy policy : polices) {
            results.add(extractor.apply(policy));
        }
        return results;
    }

    public static void notifyHolders(List<InsurancePolicy> polices, Predicate<InsurancePolicy> filter, Consumer<InsurancePolicy> notification) {
        for (InsurancePolicy policy : polices) {
            if (filter.test(policy)) {
                notification.accept(policy);
            }
        }
    }

    public static void main(String[] args) {
        List<InsurancePolicy> policies = new ArrayList<>();
        policies.add(new InsurancePolicy("POL/001", "Wacław Kąkol", "LIFE", new BigDecimal("2399.99"), true));
        policies.add(new InsurancePolicy("POL/002", "Mariola Tatar", "CAR", new BigDecimal("1199.99"), false));
        policies.add(new InsurancePolicy("POL/003", "Karol Pyc", "CAR", new BigDecimal("299.99"), true));
        policies.add(new InsurancePolicy("POL/004", "Natalia Hop", "HOME", new BigDecimal("599.99"), false));
        policies.add(new InsurancePolicy("POL/005", "Benjamin Rozer", "CAR", new BigDecimal("399.99"), true));
        policies.add(new InsurancePolicy("POL/006", "Adam Małysz", "LIFE", new BigDecimal("1599.99"), true));
        policies.add(new InsurancePolicy("POL/007", "Adrianna Kami", "HOME", new BigDecimal("199.99"), true));

        System.out.println("-----filter isActive------");
        filterPolices(policies, p -> p.isActive()).forEach(System.out::println);
        System.out.println("-----filter > 500------");
        filterPolices(policies, p -> (p.premium().compareTo(new BigDecimal("500")) > 0)).forEach(System.out::println);
        System.out.println("-----Holder names------");
        extractData(policies, InsurancePolicy::holderName).forEach(System.out::println);
        System.out.println("-----polices number------");
        extractData(policies, InsurancePolicy::policyNumber).forEach(System.out::println);
        System.out.println("-----description------");
        extractData(policies, p -> p.policyNumber() + " (" + p.type() + ")").forEach(System.out::println); // reference nie zadziała bo odnosi sie to jednego konkretnego pola z klasy, a my mamy konkatenacje wiec uzywam lambdy
        System.out.println("-----Notify------");
        notifyHolders(policies, p -> (p.type().equals("CAR") && (p.premium().compareTo(new BigDecimal("1000.00")) > 0)), p -> System.out.println("Drogi " + p.holderName() + ", Twoja polisa " + p.policyNumber() + " wymaga odnowienia"));


    }


}
