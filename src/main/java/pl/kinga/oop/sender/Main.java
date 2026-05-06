package pl.kinga.oop.sender;

public class Main {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        SmsSender smsSender = new SmsSender();
        PaperMailSender paperMailSender = new PaperMailSender();

        emailSender.send("E-23444", "Email confirmed");
        smsSender.send("S-38477", "Sms confirmed");
        paperMailSender.send("P-9021", "Mail has been send");

        emailSender.sendUrgent("E-23444", "Email confirmed");
        smsSender.sendUrgent("S-38477", "Sms confirmed");
        paperMailSender.sendUrgent("P-9021", "Mail has send");

        System.out.println("----Time----");
        System.out.println(NotificationSender.formatTimestamp());

        // 2# Shipment Task:

        Shipment shipment = new Shipment();

        System.out.println("----Shipment status----");
        System.out.println(shipment.getStatus());
    }
}
