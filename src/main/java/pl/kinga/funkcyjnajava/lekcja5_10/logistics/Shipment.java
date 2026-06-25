package pl.kinga.funkcyjnajava.lekcja5_10.logistics;

import java.util.List;

public record Shipment(String shipmentId, String carrier, String status, List<Parcel> parcels) {
}
