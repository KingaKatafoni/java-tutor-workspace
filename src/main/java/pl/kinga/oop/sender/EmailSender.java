package pl.kinga.oop.sender;

public class EmailSender implements NotificationSender {

    @Override
    public void send(String recipientId, String message) {
        System.out.println("Sending email to "
                + recipientId
                + ": "
                + message);
    }
}
