package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShippingNotifierTest {

    @Mock
    NotificationService notificationService;

    @InjectMocks
    ShippingNotifier shippingNotifier;

    @Test
    void shouldSendConfirmationWhenNotificationService() {
        Order order = new Order("ORD-001", "karo@gmail.com", 230.0, "EXPRESS");

        String message = shippingNotifier.sendConfirmation(order, 30.00);

        assertEquals("Zamowienie ORD-001 — koszt wysylki: 30.00 PLN", message);
        verify(notificationService).send("karo@gmail.com", "Zamowienie ORD-001 — koszt wysylki: 30.00 PLN");
    }

}