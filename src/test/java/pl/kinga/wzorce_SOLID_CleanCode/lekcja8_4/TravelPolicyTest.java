package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TravelPolicyTest {
    @Test
    void shouldReturnPremium60WhenTravelPolicyFor4Days() {
        Policy policy = new TravelPolicy("TRA-001", "Adam Mickiewicz", 4);
        double calculatePremium = policy.calculatePremium();

        assertEquals(60.0, calculatePremium);
    }

    @Test
    void shouldThrowIllegalArgumentWhenDurationBelow0() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new TravelPolicy("TRA-001", "Adam Mickiewicz", -1)
        );
        assertEquals("Duration must be greater than 0", ex.getMessage());
    }

}