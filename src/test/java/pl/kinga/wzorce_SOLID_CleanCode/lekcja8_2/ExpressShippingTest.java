package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressShippingTest {

    @Test
    void shouldReturn10WhenPriceAbove500() {
        Order order = new Order("ORD-001", "karo@gmail.com", 501, "EXPRESS");

        ExpressShipping expressShipping = new ExpressShipping();
        double cost = expressShipping.calculate(order);

        assertEquals(10.0, cost);
    }

    @Test
    void shouldReturn30WhenPriceBelow500() {
        Order order = new Order("ORD-001", "karo@gmail.com", 499, "EXPRESS");

        ExpressShipping expressShipping = new ExpressShipping();
        double cost = expressShipping.calculate(order);

        assertEquals(30.0, cost);
    }

}