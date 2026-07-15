package pl.kinga.testowanie.lekcja7_8;

public class ShipmentService {
    private final TrackingRepository trackingRepository;
    private final CustomerNotifier customerNotifier;

    public ShipmentService(TrackingRepository trackingRepository, CustomerNotifier customerNotifier) {
        this.trackingRepository = trackingRepository;
        this.customerNotifier = customerNotifier;
    }

    public String shipPackage(Package pkg) {
        if (pkg == null) {
            throw new IllegalArgumentException("Package cannot be null");
        }

        if (pkg.weight() <= 0) {
            throw new IllegalArgumentException("Package weight must be positive");
        }

        if (pkg.weight() > 50) {
            throw new IllegalArgumentException("Package too heavy: max 50 kg");
        }

        trackingRepository.updateStatus(pkg.trackingId(), "SHIPPED");
        customerNotifier.notifyShipped(pkg.recipientName(), pkg.trackingId());

        return "Shipped: " + pkg.trackingId();
    }

    public String deliverPackage(String trackingId) {
        if (trackingId == null || trackingId.isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be null or empty");
        }
        String status = trackingRepository.getStatus(trackingId);

        if (status == null) {
            throw new IllegalArgumentException("Package not found: " + trackingId);
        }

        if (!status.equals("SHIPPED")) {
            throw new IllegalStateException("Cannot deliver: package status is " + status);
        }

        trackingRepository.updateStatus(trackingId, "DELIVERED");

        customerNotifier.notifyDelivered(trackingId, trackingId);

        return "Delivered: " + trackingId;
    }

    public String cancelShipment(String trackingId, String reason) {
        if (trackingId == null || trackingId.isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be null or empty");
        }

        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Cancellation reason is required");
        }

        String status = trackingRepository.getStatus(trackingId);

        if (status == null) {
            throw new IllegalArgumentException("Package not found: " + trackingId);
        }

        if (status.equals("DELIVERED")) {
            throw new IllegalStateException("Cannot cancel: package already delivered");
        }

        trackingRepository.updateStatus(trackingId, "CANCELED");
        customerNotifier.notifyCancelled(trackingId, trackingId, reason);

        return "Canceled: " + trackingId + " (" + reason + ")";
    }
}
