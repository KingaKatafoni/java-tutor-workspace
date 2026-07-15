package pl.kinga.testowanie.lekcja7_6;

public interface PolicyRepository {
    Policy findById(String policyId);
    boolean exists(String policyId);
}
