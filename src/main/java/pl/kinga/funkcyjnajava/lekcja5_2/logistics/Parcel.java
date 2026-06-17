package pl.kinga.funkcyjnajava.lekcja5_2.logistics;

public record Parcel(String trackingId, String senderCity, String receiverCity, double weight, String priority) {
}
