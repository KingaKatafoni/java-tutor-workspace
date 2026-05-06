package pl.kinga.oop.sender;

public interface NotificationSender {
    void send(String recipientId, String message);

    default void sendUrgent(String recipientId, String message) {
        send(recipientId, "[URGENT] " +  message);
    }

    static String formatTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }
}
