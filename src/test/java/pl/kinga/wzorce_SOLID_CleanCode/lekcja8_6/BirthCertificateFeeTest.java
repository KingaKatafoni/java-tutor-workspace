package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirthCertificateFeeTest {

    @Test
    void shouldReturn22WhenQuantity1NotUrgent() {
        FeeStrategy feeStrategy = new BirthCertificateFee();
        double calculated = feeStrategy.calculate(1, false);

        assertEquals(22.0, calculated);
    }

    @Test
    void shouldReturn66WhenQuantity3NotUrgent() {
        FeeStrategy feeStrategy = new BirthCertificateFee();
        double calculated = feeStrategy.calculate(3, false);

        assertEquals(66.0, calculated);
    }

    @Test
    void shouldReturn33WhenQuantity1Urgent() {
        FeeStrategy feeStrategy = new BirthCertificateFee();
        double calculated = feeStrategy.calculate(1, true);

        assertEquals(33.0, calculated);
    }

    @Test
    void shouldReturn66WhenQuantity2Urgent() {
        FeeStrategy feeStrategy = new BirthCertificateFee();
        double calculated = feeStrategy.calculate(2, true);

        assertEquals(66.0, calculated);
    }

}