package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdCardFeeTest {

    @Test
    void shouldReturn0WhenQuantity1NotUrgent() {
        FeeStrategy feeStrategy = new IdCardFee();
        double calculated = feeStrategy.calculate(1, false);

        assertEquals(0.0, calculated);
    }

    @Test
    void shouldReturn30WhenQuantity1Urgent() {
        FeeStrategy feeStrategy = new IdCardFee();
        double calculated = feeStrategy.calculate(1, true);

        assertEquals(30.0, calculated);
    }

}