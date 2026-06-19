package pl.kinga.funkcyjnajava.lekcja5_4.logistics;

public record Shipment(String trackingId, String senderCity, String receiverCity, double weight, String status,
                       String type) {
}
