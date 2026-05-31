package pl.kinga.kolekcjegeneryki.logistics.logisticsfail;

public record Shipment(String trackingNumber, String status, int daysInTransit) {

    public Shipment {
        if (trackingNumber == null || trackingNumber.isEmpty() || status == null || status.isEmpty() || daysInTransit < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

}
