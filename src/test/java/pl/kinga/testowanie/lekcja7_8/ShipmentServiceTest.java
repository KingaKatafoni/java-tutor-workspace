package pl.kinga.testowanie.lekcja7_8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {
    @Mock
    TrackingRepository trackingRepository;

    @Mock
    CustomerNotifier customerNotifier;

    @InjectMocks
    ShipmentService shipmentService;

    //shipPackage
    @Test
    void shouldReturnCorrectWhenPackageCorrect() {
        Package pkg = new Package("TRK-001", "Kamil Los", "Anna Wanna", 20, "Warszawa");

        String result = shipmentService.shipPackage(pkg);
        assertEquals("Shipped: TRK-001", result);
        verify(trackingRepository).updateStatus("TRK-001", "SHIPPED");
        verify(customerNotifier).notifyShipped("Anna Wanna", "TRK-001");
    }

    @Test
    void shouldThrowIllegalArgumentWhenPkgIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.shipPackage(null));

        assertEquals("Package cannot be null", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentAndVerifyWhenWeightBelowZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.shipPackage(new Package("TRK-001", "J Lo", "Rayan Bi", -23, "Krakow")));
        assertEquals("Package weight must be positive", ex.getMessage());
        verify(trackingRepository, never()).updateStatus(anyString(), anyString());
        verify(customerNotifier, never()).notifyShipped(anyString(), anyString());
    }

    @Test
    void shouldThrowIllegalArgumentAndVerifyWhenWeightEqualsZero() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.shipPackage(new Package("TRK-001", "J Lo", "Rayan Bi", 0, "Krakow")));
        assertEquals("Package weight must be positive", ex.getMessage());
    }

    @Test
    void shouldReturnIllegalArgumentWhenWeightAbove50Kg() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.shipPackage(new Package("TRK-001", "J Lo", "Anna Wanna", 56, "Warszawa")));

        assertEquals("Package too heavy: max 50 kg", ex.getMessage());
    }

    //deliverPackage
    @Test
    void shouldReturnDeliveredWhenStatusShipped() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn("SHIPPED");

        String result = shipmentService.deliverPackage("TRK-001");

        assertEquals("Delivered: TRK-001", result);
        verify(trackingRepository).updateStatus("TRK-001", "DELIVERED");
        verify(customerNotifier).notifyDelivered(anyString(), anyString());
    }

    @Test
    void shouldThrowIllegalStateWhenStatusCancelled() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn("CANCELLED");
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> shipmentService.deliverPackage("TRK-001"));

        assertEquals("Cannot deliver: package status is CANCELLED", ex.getMessage());
        verify(trackingRepository, never()).updateStatus("TRK-001", "DELIVERED");
        verify(customerNotifier, never()).notifyDelivered(anyString(), anyString());
    }

    @Test
    void shouldThrowIllegalArgumentWhenStatusNull() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.deliverPackage("TRK-001"));
        assertEquals("Package not found: TRK-001", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenTrackingIdIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.deliverPackage(null));

        assertEquals("Tracking ID cannot be null or empty", ex.getMessage());
    }


    @Test
    void shouldThrowIllegalArgumentWhenTrackingIdIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.deliverPackage(""));

        assertEquals("Tracking ID cannot be null or empty", ex.getMessage());
    }

    //cancelShipment

    @Test
    void shouldReturnCancelledWhenStatusShipped() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn("SHIPPED");

        String result = shipmentService.cancelShipment("TRK-001", "Ship has sink");
        assertEquals("Canceled: TRK-001 (Ship has sink)", result);
        verify(trackingRepository).updateStatus("TRK-001", "CANCELED");
        verify(customerNotifier).notifyCancelled("TRK-001", "TRK-001", "Ship has sink");
    }

    @Test
    void shouldThrowIllegalStateWhenStatusDelivered() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn("DELIVERED");

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> shipmentService.cancelShipment("TRK-001", "Ship has sink"));
        assertEquals("Cannot cancel: package already delivered", ex.getMessage());
        verify(trackingRepository, never()).updateStatus("TRK-001", "CANCELED");
        verify(customerNotifier, never()).notifyCancelled(anyString(), anyString(), anyString());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPackageDoesntExist() {
        when(trackingRepository.getStatus("TRK-001")).thenReturn(null);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.cancelShipment("TRK-001", "Ship has sink"));

        assertEquals("Package not found: TRK-001", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentCancelShipmentWhenTrackingIdIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.cancelShipment(null, "Ship is sink"));

        assertEquals("Tracking ID cannot be null or empty", ex.getMessage());

    }

    @Test
    void shouldThrowIllegalArgumentWhenReasonIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> shipmentService.cancelShipment("TRK-001", null));

        assertEquals("Cancellation reason is required", ex.getMessage());
    }


}
