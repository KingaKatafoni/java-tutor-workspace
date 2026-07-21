package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import java.util.Locale;

public class ShippingNotifier {
    private final NotificationService notificationService;

    public ShippingNotifier(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String sendConfirmation(Order order, double shippingCost) {
        String message = String.format(Locale.US, "Zamowienie %s — koszt wysylki: %.2f PLN", order.orderId(), shippingCost);
        notificationService.send(order.customerEmail(), message);
        return message;
    }
}
