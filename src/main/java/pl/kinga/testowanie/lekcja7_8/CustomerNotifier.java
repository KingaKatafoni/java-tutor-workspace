package pl.kinga.testowanie.lekcja7_8;

public interface CustomerNotifier {
    void notifyShipped(String recipientName, String trackingId);

    void notifyDelivered(String recipientName, String trackingId);

    void notifyCancelled(String recipientName, String trackingId, String reason);
}
