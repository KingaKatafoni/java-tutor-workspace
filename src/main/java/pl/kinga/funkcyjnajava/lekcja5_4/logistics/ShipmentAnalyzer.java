package pl.kinga.funkcyjnajava.lekcja5_4.logistics;

import java.util.List;

public class ShipmentAnalyzer {
    public static void main(String[] args) {
        List<Shipment> shipments = List.of(
                new Shipment("TRAC/001", "Poznan", "Bydgoszcz", 123.5, "DELIVERED", "STANDARD"),
                new Shipment("TRAC/002", "Berlin", "Krakow", 23.5, "DISPATCH", "EXPRESS"),
                new Shipment("TRAC/003", "Krakow", "Warszawa", 3.5, "DELIVERED", "STANDARD"),
                new Shipment("TRAC/004", "Warszawa", "Bydgoszcz", 76.5, "IN_TRANSIT", "EXPRESS"),
                new Shipment("TRAC/005", "Warszawa", "Poznan", 523.0, "DISPATCH", "FRAGILE"),
                new Shipment("TRAC/006", "Rzeszow", "Konin", 13.0, "IN_TRANSIT", "FRAGILE"),
                new Shipment("TRAC/007", "Konin", "Bydgoszcz", 1.5, "DELIVERED", "STANDARD"),
                new Shipment("TRAC/008", "Konin", "Warszawa", 24.5, "IN_TRANSIT", "FRAGILE"),
                new Shipment("TRAC/009", "Warszawa", "Berlin", 55.5, "DISPATCH", "STANDARD"),
                new Shipment("TRAC/010", "Berlin", "Poznan", 31.0, "RETURNED", "EXPRESS")
        );

        System.out.println("-----Packages in Transit----");
        List<Shipment> intransitPackages = shipments.stream()
                .filter(p -> p.status().equals("IN_TRANSIT"))
                .toList();
        intransitPackages.forEach(System.out::println);

        System.out.println("----Delivered tracking Id-----");
        List<String> deliverdId = shipments.stream()
                .filter(p -> p.status().equals("DELIVERED"))
                .map(Shipment::trackingId)
                .toList();
        deliverdId.forEach(System.out::println);

        System.out.println("---Unique Sender City---");
        List<String> uniqueSenderCity = shipments.stream()
                .map(Shipment::senderCity)
                .distinct()
                .toList();

        uniqueSenderCity.forEach(System.out::println);

        System.out.println("----Express Packages Count----");
        long expressCount = shipments.stream()
                .filter(p -> p.type().equals("EXPRESS"))
                .count();
        System.out.println("Express: " + expressCount);

        System.out.println("---Heavy Packages in transit---");
        List<Shipment> heavyInTransit = shipments.stream()
                .filter(p -> p.weight() > 20.0)
                .filter(p -> p.status().equals("IN_TRANSIT"))
                .toList();
        heavyInTransit.forEach(System.out::println);

        System.out.println("---Returned Packages---");
        List<String> returned = shipments.stream()
                .filter(p -> p.status().equals("RETURNED"))
                .map(p -> "RETURNED: " + p.trackingId() + " from " + p.senderCity() + " to " + p.receiverCity())
                .toList();
        returned.forEach(System.out::println);
    }


}
