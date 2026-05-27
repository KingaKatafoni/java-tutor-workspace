package pl.kinga.kolekcjegeneryki.publicsector.publicsectorcompare;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        EmployeeDirectory employeeDirectory = new EmployeeDirectory();
        Employee employee1 = new Employee("Nowak", "Jan", new BigDecimal("3500.99"), 15);
        Employee employee2 = new Employee("Nowak", "Adam", new BigDecimal("13500.99"), 5);
        Employee employee3 = new Employee("Kosela", "Zygfryd", new BigDecimal("2500.00"), 11);
        Employee employee4 = new Employee("Baran", "Karolina", new BigDecimal("34500.99"), 1);
        Employee employee5 = new Employee("Amadeusz", "Mozart", new BigDecimal("1500.99"), 25);
        Employee employee6 = new Employee("Romski", "Jan", new BigDecimal("10500.99"), 10);

        employeeDirectory.addEmployee(employee1);
        employeeDirectory.addEmployee(employee2);
        employeeDirectory.addEmployee(employee3);
        employeeDirectory.addEmployee(employee4);
        employeeDirectory.addEmployee(employee5);
        employeeDirectory.addEmployee(employee6);

        employeeDirectory.printAll();
        System.out.println();
        System.out.println("-----sortNatural()------");
        employeeDirectory.sortNatural();
        employeeDirectory.printAll();
        System.out.println();
        System.out.println("------sortByLastNameThenFirstName()------");
        employeeDirectory.sortByLastNameThenFirstName();
        employeeDirectory.printAll();
        System.out.println();
        System.out.println("-----sortBySalary()--------");
        employeeDirectory.sortBySalary();
        employeeDirectory.printAll();
        System.out.println();
        System.out.println("------sortBySalaryDescending()------");
        employeeDirectory.sortBySalaryDescending();
        employeeDirectory.printAll();
    }
}
