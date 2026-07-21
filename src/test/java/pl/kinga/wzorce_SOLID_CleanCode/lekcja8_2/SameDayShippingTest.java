package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SameDayShippingTest {
    @Test
    void shouldReturn50WhenSameDay() {
        Order order = new Order("ORD-001", "karo@gmail.com", 499, "SAME_DAY");

        SameDayShipping sameDayShipping = new SameDayShipping();
        double cost = sameDayShipping.calculate(order);

        assertEquals(50.0, cost);
    }

}