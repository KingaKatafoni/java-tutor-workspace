package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Test
    void shouldReturnConfirmationWhenPlaceOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 34.0);
        OrderService orderService = new OrderService();

        String confirmation = orderService.placeOrder(order);
        assertEquals("Order placed: ORD-001", confirmation);
    }

    @Test
    void shouldThrowIllegalArgumentWhenPlaceOrderNull() {
        OrderService orderService = new OrderService();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(null));

        assertEquals("Order cannot be null", ex.getMessage());
    }

    @Test
    void shouldReturn1WhenAddObserver1() {
        OrderObserver observer = new EmailObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer);
        int observersCount = orderService.getObserversCount();

        assertEquals(1, observersCount);
    }

    @Test
    void shouldReturn3WhenAddObserver3() {
        OrderObserver observer1 = new EmailObserver();
        OrderObserver observer2 = new WarehouseObserver();
        OrderObserver observer3 = new InvoiceObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer1);
        orderService.addObserver(observer2);
        orderService.addObserver(observer3);

        int observersCount = orderService.getObserversCount();

        assertEquals(3, observersCount);
    }

    @Test
    void shouldReturn2WhenAddObserver3Remove1() {
        OrderObserver observer1 = new EmailObserver();
        OrderObserver observer2 = new EmailObserver();
        OrderObserver observer3 = new EmailObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer1);
        orderService.addObserver(observer2);
        orderService.addObserver(observer3);

        int observersCountBeforeRemove = orderService.getObserversCount();
        orderService.removeObserver(observer1);
        int observersCountAfterRemove = orderService.getObserversCount();

        assertEquals(3, observersCountBeforeRemove);
        assertEquals(2, observersCountAfterRemove);
    }

    @Test
    void shouldReturnEmailConfirmationWhenPlaceOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 34.0);
        EmailObserver observer = new EmailObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer);
        orderService.placeOrder(order);
        String notification = observer.getSentEmails().get(0);

        assertEquals("Email to karo@gmail.com for order ORD-001", notification);

    }

    @Test
    void shouldReturnWarehouseConfirmationWhenPlaceOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 34.0);
        WarehouseObserver observer = new WarehouseObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer);
        orderService.placeOrder(order);
        String notification = observer.getPreparedOrders().get(0);

        assertEquals("Prepare package: ORD-001", notification);

    }

    @Test
    void shouldReturnInvoiceConfirmationWhenPlaceOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 34.0);
        InvoiceObserver observer = new InvoiceObserver();
        OrderService orderService = new OrderService();
        orderService.addObserver(observer);
        orderService.placeOrder(order);
        String notification = observer.getGeneratedInvoices().get(0);

        assertEquals("Invoice for ORD-001: 34.0 PLN", notification);

    }

    @Test
    void shouldNotify2TimesWhenPlacedOrder2Times() {
        Order order1 = new Order("ORD-001", "karo@gmail.com", 34.0);
        Order order2 = new Order("ORD-002", "anna@gmail.com", 124.0);

        EmailObserver observer = new EmailObserver();

        OrderService orderService = new OrderService();
        orderService.addObserver(observer);

        orderService.placeOrder(order1);
        orderService.placeOrder(order2);

        assertEquals(2, observer.getSentEmails().size());
    }

    @Test
    void shouldNotThrowIllegalStateWhenNoObservers(){
        OrderService orderService = new OrderService();
        Order order = new Order("ORD-001", "karo@gmail.com", 34.0);
        orderService.placeOrder(order);

        assertDoesNotThrow(() -> orderService.placeOrder(order));

    }


}