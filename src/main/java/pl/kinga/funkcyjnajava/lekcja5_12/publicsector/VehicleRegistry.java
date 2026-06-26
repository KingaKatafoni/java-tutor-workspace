package pl.kinga.funkcyjnajava.lekcja5_12.publicsector;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class VehicleRegistry {
    public static void main(String[] args) {
        List<Vehicle> vehicles = List.of(
                new Vehicle("WA 12345", "Anna Kowalska", "Toyota", 2020, "VALID", "PZU"),
                new Vehicle("KR 98765", "Jan Nowak", "BMW", 2018, "EXPIRED", null),
                new Vehicle("PO 11111", "Maria Wiszniewska", "Skoda", 2022, "VALID", "Warta"),
                new Vehicle("GD 55555", "Piotr Zielinski", "Ford", 2015, "PENDING", null),
                new Vehicle("WA 99999", "Ewa Dabrowska", "Volkswagen", 2021, "VALID", "Allianz"),
                new Vehicle("KR 22222", "Tomasz Lewandowski", "Audi", 2019, "EXPIRED", "PZU"),
                new Vehicle("WR 33333", "Katarzyna Wojcik", "Hyundai", 2023, "VALID", null),
                new Vehicle("PO 44444", "Michal Kaminski", "Renault", 2017, "EXPIRED", "Warta")
        );

        System.out.println("---- 1# Find vehicle by plate number PO 11111 ----");
        Optional<Vehicle> vehiclePo11111 = vehicles.stream()
                .filter(v -> v.plateNumber().equals("PO 11111"))
                .findFirst();
        vehiclePo11111.ifPresent(System.out::println);

        System.out.println("---- 2# Find vehicle by plate number XX 00000 ----");
        vehicles.stream()
                .filter(v -> v.plateNumber().equals("XX 00000"))
                .findFirst()
                .ifPresentOrElse(
                        v -> System.out.println("Znaleziono: " + v),
                        () -> System.out.println("Nie znaleziono pojazdu XX 00000")
                );

        System.out.println("---- 3# The oldest car ----");
        Vehicle theOldest = vehicles.stream()
                .min(Comparator.comparing(Vehicle::year))
                .orElseThrow();
        System.out.println(theOldest);

        System.out.println("---- 4# Insurer of vehicle KR 98765 ----");
        Vehicle vehicleKR98765 = vehicles.stream()
                .filter(v -> v.plateNumber().equals("KR 98765"))
                .findFirst()
                .orElseThrow();


        String insurerVehicleKR98765 = Optional.ofNullable(vehicleKR98765.insurerName())
                .orElse("BRAK UBEZPIECZENIA");
        System.out.println(insurerVehicleKR98765);

        System.out.println("---- 5# Insurer of vehicle WA 12345 ----");
        Vehicle vehicleWA12345 = vehicles.stream()
                .filter(v -> v.plateNumber().equals("WA 12345"))
                .findFirst()
                .orElseThrow();


        String insurerWA12345 = Optional.ofNullable(vehicleWA12345.insurerName())
                .orElse("BRAK UBEZPIECZENIA");
        System.out.println(insurerWA12345);

        System.out.println("---- 6# First vehicle with valid inspection status and newer then 2020 ----");
        vehicles.stream()
                .filter(v -> v.inspectionStatus().equals("VALID"))
                .filter(v -> v.year() > 2020)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Nie znaleziono")
                );

        System.out.println("---- 7# How many vehicle dont have insurance? ----");
        long noInsurance = vehicles.stream()
                .filter(v -> v.insurerName() == null)
                .count();

        long noInsurance2 = vehicles.stream()
                .filter(v -> Optional.ofNullable(v.insurerName()).isEmpty()) // ten sie wydaje mniej czytelny intuicyjnie bym porownała do null
                .count();
        System.out.println("Pierwszy sposob" + noInsurance);
        System.out.println("Drugi sposob" + noInsurance2);


    }
}
