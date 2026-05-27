package pl.kinga.kolekcjegeneryki.publicsector.publicsectorcompare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeDirectory {
    private List<Employee> employees;

    public EmployeeDirectory() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e){
        employees.add(e);
    }

    public void sortNatural() {
        Collections.sort(employees);
    }

    public void sortBySalary() {
        employees.sort(Comparator.comparing(Employee::salary));
    }

    public void sortBySalaryDescending() {
        employees.sort(Comparator.comparing(Employee::salary).reversed());
    }

    public void sortByLastNameThenFirstName() {
        employees.sort(Comparator.comparing(Employee::lastName).thenComparing(Employee::firstName));
    }

    public void printAll() {
        for (Employee e : employees){
            System.out.println(e);
        }
    }
}
