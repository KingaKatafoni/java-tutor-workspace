package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomePolicyTest {
    @Test
    void shouldReturnPremium250WhenArea50() {
        Policy policy = new HomePolicy("HOM-001", "Adam Mickiewicz", 50);
        double calculatePremium = policy.calculatePremium();

        assertEquals(250.0, calculatePremium);
    }

    @Test
    void shouldThrowIllegalArgumentWhenAreaBelow0() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new HomePolicy("HOM-001", "Adam Mickiewicz", -1));
        assertEquals("Area must be positive", ex.getMessage());
    }

}