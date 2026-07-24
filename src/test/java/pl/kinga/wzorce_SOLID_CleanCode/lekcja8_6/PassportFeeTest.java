package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassportFeeTest {

    @Test
    void shouldReturn140WhenQuantity1NotUrgent() {
        FeeStrategy feeStrategy = new PassportFee();
        double calculated = feeStrategy.calculate(1, false);

        assertEquals(140.0, calculated);
    }

    @Test
    void shouldReturn240WhenQuantity1Urgent() {
        FeeStrategy feeStrategy = new PassportFee();
        double calculated = feeStrategy.calculate(1, true);

        assertEquals(240.0, calculated);
    }

}