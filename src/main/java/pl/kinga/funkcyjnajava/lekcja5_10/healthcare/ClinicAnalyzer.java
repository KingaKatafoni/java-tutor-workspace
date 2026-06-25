package pl.kinga.funkcyjnajava.lekcja5_10.healthcare;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class ClinicAnalyzer {
    public static void main(String[] args) {
        List<Clinic> clinics = List.of(
                new Clinic("MediCare", "Warszawa", List.of(
                        new Doctor("Anna", "Kowalska", "Cardiology", new BigDecimal("18500.00")),
                        new Doctor("Bartek", "Nowak", "Neurology", new BigDecimal("17200.00")),
                        new Doctor("Celina", "Zielinska", "Cardiology", new BigDecimal("19800.00"))
                )),
                new Clinic("HealthPlus", "Warszawa", List.of(
                        new Doctor("Dorota", "Baran", "Dermatology", new BigDecimal("14500.00")),
                        new Doctor("Emil", "Lis", "Orthopedics", new BigDecimal("16800.00"))
                )),
                new Clinic("VitaMed", "Krakow", List.of(
                        new Doctor("Filip", "Wojcik", "Neurology", new BigDecimal("19500.00")),
                        new Doctor("Grazyna", "Kaminska", "Dermatology", new BigDecimal("13900.00")),
                        new Doctor("Henryk", "Pawlak", "Cardiology", new BigDecimal("17200.00")),
                        new Doctor("Irena", "Dabrowska", "Orthopedics", new BigDecimal("20200.00"))
                )),
                new Clinic("CityMed", "Poznan", List.of(
                        new Doctor("Jan", "Mazur", "Neurology", new BigDecimal("21000.00")),
                        new Doctor("Katarzyna", "Krawczyk", "Cardiology", new BigDecimal("15500.00"))
                )),
                new Clinic("SanaMed", "Gdansk", List.of(
                        new Doctor("Leon", "Piotrowski", "Dermatology", new BigDecimal("13500.00")),
                        new Doctor("Maria", "Grabowska", "Orthopedics", new BigDecimal("15800.00")),
                        new Doctor("Norbert", "Walczak", "Neurology", new BigDecimal("16200.00"))
                ))
        );

        System.out.println("----1# List of all doctors----");
        List<String> listOfAllDoctors = clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .map(d -> d.firstName() + " " + d.lastName())
                .toList();
        listOfAllDoctors.forEach(System.out::println);

        System.out.println("----2# Amount of all doctors----");
        long countAllDoctors = clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .count();
        System.out.println(countAllDoctors);

        System.out.println("----3# Total sum of all salaries----");
        BigDecimal totalSalary = clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .map(Doctor::monthlySalary)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
        System.out.println(totalSalary + " PLN");

        System.out.println("----4# The best paid doctor----");
        clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .max(Comparator.comparing(Doctor::monthlySalary))
                .ifPresent(System.out::println);

        System.out.println("----5# All doctors sorted by lastName----");
        List<Doctor> allDoctorsSortedByLastName = clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .sorted(Comparator.comparing(Doctor::lastName))
                .toList();
        allDoctorsSortedByLastName.forEach(System.out::println);

        System.out.println("----6# Unique cities where clinics are----");
        List<String> uniqueCities = clinics.stream()
                .map(Clinic::city)
                .distinct()
                .toList();
        uniqueCities.forEach(System.out::println); // tu map a nie flatMap poniewaz nie potrzebujemy informacji z zagniezdzonej kolekcji tylko z kolekcji głownej clinics

        System.out.println("----7# Names of doctors with salary above 17000----");
        List<String> namesDoctorsSalaryAbove17K = clinics.stream()
                .flatMap(cli -> cli.doctors().stream())
                .filter(d -> d.monthlySalary().compareTo(new BigDecimal("17000.00")) > 0)
                .map(Doctor::lastName)
                .toList();
        namesDoctorsSalaryAbove17K.forEach(System.out::println);

        System.out.println("----8# Average salary of all doctors----");
        BigDecimal averageSalary = totalSalary.divide(new BigDecimal(countAllDoctors), 2, RoundingMode.HALF_UP);
        System.out.println(averageSalary + " PLN");


    }
}
