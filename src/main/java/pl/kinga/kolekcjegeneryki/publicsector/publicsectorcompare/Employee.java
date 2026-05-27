package pl.kinga.kolekcjegeneryki.publicsector.publicsectorcompare;

import java.math.BigDecimal;

public record Employee(String lastName, String firstName, BigDecimal salary,
                       int yearsOfService) implements Comparable<Employee> {

    public Employee {
        if (lastName == null || lastName.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                salary == null || salary.compareTo(BigDecimal.ZERO) < 0 || yearsOfService < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

    @Override
    public int compareTo(Employee o) {
        return this.lastName.compareTo(o.lastName);
    }
}
