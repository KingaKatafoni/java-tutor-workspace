package pl.kinga.funkcyjnajava.lekcja5_10.logistics;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShipmentAnalyzer {
    public static void main(String[] args) {
        List<Shipment> shipments = List.of(
                new Shipment("SHP001", "DHL", "DELIVERED", List.of(
                        new Parcel("TR001", 2.5, "Warszawa"),
                        new Parcel("TR002", 0.8, "Krakow"),
                        new Parcel("TR003", 5.2, "Warszawa")
                )),
                new Shipment("SHP002", "InPost", "IN_TRANSIT", List.of(
                        new Parcel("TR004", 1.1, "Gdansk"),
                        new Parcel("TR005", 3.7, "Poznan")
                )),
                new Shipment("SHP003", "DPD", "PREPARING", List.of(
                        new Parcel("TR006", 0.3, "Wroclaw"),
                        new Parcel("TR007", 12.0, "Krakow"),
                        new Parcel("TR008", 0.5, "Wroclaw"),
                        new Parcel("TR009", 2.1, "Gdansk")
                )),
                new Shipment("SHP004", "DHL", "DELIVERED", List.of(
                        new Parcel("TR010", 8.4, "Poznan"),
                        new Parcel("TR011", 1.9, "Warszawa"),
                        new Parcel("TR012", 0.6, "Poznan")
                )),
                new Shipment("SHP005", "InPost", "RETURNED", List.of(
                        new Parcel("TR013", 4.0, "Krakow"),
                        new Parcel("TR014", 1.5, "Gdansk")
                ))
        );

        System.out.println("----1# List of all tracking number----");
        List<String> listOfAllTrackingNumb = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .map(Parcel::trackingNumber)
                .toList();
        listOfAllTrackingNumb.forEach(System.out::println);

        System.out.println("----2# Total amount of packages----");
        long amountOfPackages = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .count();
        System.out.println(amountOfPackages);

        System.out.println("----3# Total weight of parcels----");
        double totalWeight = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .mapToDouble(Parcel::weightKg)
                .sum();
        System.out.println(totalWeight + " kg");

        System.out.println("----4# Unique destinations sorted alphabetically----");
        List<String> uniqueCities = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .map(Parcel::destination)
                .distinct()
                .sorted()
                .toList();
        uniqueCities.forEach(System.out::println);

        System.out.println("----5# Heaviest parcel (Parcel Object)----");
        Optional<Parcel> heaviestParcel = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .max(Comparator.comparing(Parcel::weightKg));
        heaviestParcel.ifPresent(System.out::println);

        System.out.println("----6# All parcels DELIVERED----");
        List<Parcel> deliveredParcels = shipments.stream()
                .filter(p -> p.status().equals("DELIVERED"))
                .flatMap(par -> par.parcels().stream())
                .toList(); // filter przed flatMap() bo chcemy filtrować po kolekcji nadrzędnej a nie zagnieżdżonej

        deliveredParcels.forEach(System.out::println);

        System.out.println("----7# How much send to Krakow----");
        long sendToKrakow = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .filter(p -> p.destination().equals("Krakow"))
                .count();
        System.out.println(sendToKrakow);

        System.out.println("----8# Parcel number with weight heavier than 3kg----");
        List<String> packagesHeavierThan3Kg = shipments.stream()
                .flatMap(par -> par.parcels().stream())
                .filter(p -> p.weightKg() > 3.0)
                .map(Parcel::trackingNumber)
                .toList();
        packagesHeavierThan3Kg.forEach(System.out::println);

    }
}
