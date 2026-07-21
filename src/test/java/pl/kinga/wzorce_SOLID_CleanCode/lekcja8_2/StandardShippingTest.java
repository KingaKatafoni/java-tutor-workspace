package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardShippingTest {

    @Test
    void shouldReturn0WhenPriceAbove200(){
        Order order = new Order("ORD-001", "karo@gmail.com", 201,"STANDARD");

        StandardShipping standardShipping = new StandardShipping();
        double cost = standardShipping.calculate(order);

        assertEquals(0.0, cost);
    }

    @Test
    void shouldReturn15WhenPriceBelow200(){
        Order order = new Order("ORD-001", "karo@gmail.com", 199,"STANDARD");

        StandardShipping standardShipping = new StandardShipping();
        double cost = standardShipping.calculate(order);

        assertEquals(15.0, cost);
    }

    @Test
    void shouldReturn0WhenPrice0(){
        Order order = new Order("ORD-001", "karo@gmail.com", 0.0,"STANDARD");

        StandardShipping standardShipping = new StandardShipping();
        double cost = standardShipping.calculate(order);

        assertEquals(15.0, cost);
    }

}