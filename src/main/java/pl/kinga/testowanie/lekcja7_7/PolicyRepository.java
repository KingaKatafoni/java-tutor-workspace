package pl.kinga.testowanie.lekcja7_7;

public interface PolicyRepository {
    Policy findById(String policyId);
    boolean exists(String policyId);
}
