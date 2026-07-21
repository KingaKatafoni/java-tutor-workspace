package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PickupShippingTest {
    @Test
    void shouldReturn0WhenPickUp() {
        Order order = new Order("ORD-001", "karo@gmail.com", 99, "PICKUP");

        PickupShipping pickupShipping = new PickupShipping();
        double cost = pickupShipping.calculate(order);

        assertEquals(0.0, cost);
    }

}