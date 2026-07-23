package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarPolicyTest {
    @Test
    void shouldReturnPremium1800WhenAgeBelow25() {
        CarPolicy carPolicy = new CarPolicy("CAR-001", "Adam Mickiewicz", 24);
        double premium = carPolicy.calculatePremium();

        assertEquals(1800.0, premium);
    }

    @Test
    void shouldReturnPremium1500WhenAgeAbove65() {
        CarPolicy carPolicy = new CarPolicy("CAR-001", "Adam Mickiewicz", 66);
        double premium = carPolicy.calculatePremium();

        assertEquals(1500.0, premium);
    }

    @Test
    void shouldReturnPremium1200WhenAgeBetween25And65() {
        CarPolicy carPolicy = new CarPolicy("CAR-001", "Adam Mickiewicz", 26);
        double premium = carPolicy.calculatePremium();

        assertEquals(1200.0, premium);
    }

    @Test
    void shouldReturnCoverage50000WhenCarPolicy() {
        CarPolicy carPolicy = new CarPolicy("CAR-001", "Adam Mickiewicz", 24);
        double coverage = carPolicy.getCoverage();

        assertEquals(50000.0, coverage);
    }

    @Test
    void shouldThrowIllegalArgumentWhenAgeBelowZero() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CarPolicy("CAR-001", "Adam Mickiewicz", -1));
        assertEquals("Age must be positive", ex.getMessage());
    }

}