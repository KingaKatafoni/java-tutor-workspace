package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShippingCostCalculatorTest {
    @Mock
    ShippingStrategy shippingStrategy;

    @InjectMocks
    ShippingCostCalculator shippingCostCalculator;

    @Test
    void shouldReturn15WhenStandardOrderBelow200() {
        Order order = new Order("ORD-001", "karo@gmail.com", 130.0, "STANDARD");
        when(shippingStrategy.calculate(order)).thenReturn(15.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(15.0, v);
    }

    @Test
    void shouldReturn0WhenStandardOrderAbove200() {
        Order order = new Order("ORD-001", "karo@gmail.com", 130.0, "STANDARD");
        when(shippingStrategy.calculate(order)).thenReturn(0.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(0.0, v);
    }

    @Test
    void shouldReturn30WhenExpressOrderBelow500() {
        Order order = new Order("ORD-001", "karo@gmail.com", 450.0, "EXPRESS");
        when(shippingStrategy.calculate(order)).thenReturn(30.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(30.0, v);
    }

    @Test
    void shouldReturn10WhenExpressOrderAbove500() {
        Order order = new Order("ORD-001", "karo@gmail.com", 530.0, "EXPRESS");
        when(shippingStrategy.calculate(order)).thenReturn(10.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(10.0, v);
    }


    @Test
    void shouldReturn50WhenSameDayOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 230.0, "SAME DAY");
        when(shippingStrategy.calculate(order)).thenReturn(50.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(50.0, v);
    }

    @Test
    void shouldReturn0WhenPickUpOrder() {
        Order order = new Order("ORD-001", "karo@gmail.com", 230.0, "PICKUP");
        when(shippingStrategy.calculate(order)).thenReturn(0.0);

        double v = shippingCostCalculator.calculateShippingCost(order);
        assertEquals(0.0, v);
    }

    @Test
    void shouldThrowIllegalArgumentWhenOrderNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new ShippingCostCalculator(null));

        assertEquals("Shipping strategy is required", ex.getMessage());

    }


}