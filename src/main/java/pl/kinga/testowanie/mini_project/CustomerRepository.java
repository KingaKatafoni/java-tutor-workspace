package pl.kinga.testowanie.mini_project;

public interface CustomerRepository {
    Customer findById(String customerId);
}
