package pl.kinga.testowanie.lekcja7_8;

public interface TrackingRepository {
    void updateStatus(String trackingId, String status);

    String getStatus(String trackingId);
}
