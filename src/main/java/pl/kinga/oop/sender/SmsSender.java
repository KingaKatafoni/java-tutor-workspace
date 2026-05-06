package pl.kinga.oop.sender;

public class SmsSender implements NotificationSender {
    @Override
    public void send(String recipientId, String message) {
        System.out.println("Sending SMS to "
                + recipientId
                + ": "
                + message);
    }
}
