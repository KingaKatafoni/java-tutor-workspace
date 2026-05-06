package pl.kinga.oop.sender;

public class PaperMailSender implements NotificationSender {
    @Override
    public void send(String recipientId, String message) {
        System.out.println("Sending paper mail to "
                + recipientId
                + ": "
                + message);
    }

    @Override
    public void sendUrgent(String recipientId, String message) {
        System.out.println("URGENT paper mail requires courier delivery to "
                + recipientId
                + ": "
                + message);
    }

}
