package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

public class EmailService implements NotificationService {
    public void send(String to, String message) {
        System.out.println("Sending email to " + to + ": " + message);
    }
}
