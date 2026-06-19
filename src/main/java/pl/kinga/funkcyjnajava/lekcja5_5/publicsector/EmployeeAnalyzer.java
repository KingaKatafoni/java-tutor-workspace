package pl.kinga.funkcyjnajava.lekcja5_5.publicsector;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;
import java.util.List;

public class EmployeeAnalyzer {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("EMP/001", "Jan", "Kowalski", "IT", new BigDecimal("18900.00"), 6, "SPECIALIST"),
                new Employee("EMP/002", "Kinga", "Binga", "FINANCE", new BigDecimal("7500.00"), 2, "SENIOR"),
                new Employee("EMP/003", "Arnold", "Boczek", "HR", new BigDecimal("8500.00"), 10, "SPECIALIST"),
                new Employee("EMP/004", "Amanda", "Kowalska", "HR", new BigDecimal("10900.00"), 4, "MANAGER"),
                new Employee("EMP/005", "Zygmunt", "Stary", "FINANCE", new BigDecimal("10500.00"), 2, "MID"),
                new Employee("EMP/006", "Wladyslaw", "Adapter", "HR", new BigDecimal("28500.00"), 15, "SPECIALIST"),
                new Employee("EMP/007", "Jerzy", "Kowal", "IT", new BigDecimal("6900.00"), 1, "MANAGER"),
                new Employee("EMP/008", "Kinga", "Lutnik", "FINANCE", new BigDecimal("5500.00"), 3, "SENIOR"),
                new Employee("EMP/009", "Barbara", "Bok", "IT", new BigDecimal("12500.00"), 20, "JUNIOR"),
                new Employee("EMP/010", "Ireneusz", "Krol", "HR", new BigDecimal("4500.00"), 30, "SENIOR"),
                new Employee("EMP/011", "Cecylia", "Waliga", "LEGAL", new BigDecimal("23500.00"), 12, "JUNIOR")
        );

        System.out.println("----IT Employee----");
        List<String> itEmployee = employees.stream()
                .filter(e -> e.department().equals("IT"))
                .map(p -> p.firstName() + " " + p.lastName())
                .toList();
        itEmployee.forEach(System.out::println);

        System.out.println("----Employee with salary more than 8000----");
        List<String> idSalaryAbove8000 = employees.stream()
                .filter(e -> e.salary().compareTo(new BigDecimal("8000")) > 0)
                .map(Employee::employeeId)
                .toList();
        idSalaryAbove8000.forEach(System.out::println);

        System.out.println("----Average salary in FINANCE department----");
        double averageSalaryInFinances = employees.stream()
                .filter(e -> e.department().equals("FINANCE"))
                .mapToDouble(e -> e.salary().doubleValue())
                .average()
                .orElse(0.0);
        System.out.printf("Average salary in FINANCE: %.2f%n", averageSalaryInFinances);

        System.out.println("----Salary sum all managers----");
        double sumSalaryManagers = employees.stream()
                .filter(e -> e.position().equals("MANAGER"))
                .mapToDouble(e -> e.salary().doubleValue())
                .sum();
        System.out.printf("Salary sum all managers: %.2f%n", sumSalaryManagers);

        System.out.println("----Max salary in Company----");
        double maxSalary = employees.stream()
                .mapToDouble(e -> e.salary().doubleValue())
                .max()
                .orElse(0.0);
        System.out.printf("Max salary in Company: %.2f%n", maxSalary);

        System.out.println("----Years of service statistics----");
        IntSummaryStatistics yearsOfServiceStatistics = employees.stream()
                .mapToInt(Employee::yearsOfService)
                .summaryStatistics();

        System.out.println("Count: " + yearsOfServiceStatistics.getCount());
        System.out.printf("Avg: %.2f%n", yearsOfServiceStatistics.getAverage());
        System.out.println("Min: " + yearsOfServiceStatistics.getMin());
        System.out.println("Max: " + yearsOfServiceStatistics.getMax());

        System.out.println("----Unique departments where SENIOR----");
        List<String> uniqueSeniorDepartments = employees.stream()
                .filter(e -> e.position().equals("SENIOR"))
                .map(Employee::department)
                .distinct()
                .toList();
        uniqueSeniorDepartments.forEach(System.out::println);

        System.out.println("----Capitalized last names + alphabetic order----");
        List<String> capitalizedSortedLastNames = employees.stream()
                .map(Employee::lastName)
                .map(String::toUpperCase)
                .sorted()
                .toList();

        capitalizedSortedLastNames.forEach(System.out::println);

    }
}
