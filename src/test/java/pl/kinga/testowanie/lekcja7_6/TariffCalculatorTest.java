package pl.kinga.testowanie.lekcja7_6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TariffCalculatorTest {
    TariffCalculator tariffCalculator;

    @BeforeEach
    void setUp() {
        tariffCalculator = new TariffCalculator();
    }

    //calculateMonthlyCost
    @ParameterizedTest(name = "{0}, {1} min, {2} GB -> {3}")
    @CsvSource({
            "BASIC, 50, 1, 29.99",
            "BASIC, 150, 5, 74.49",
            "STANDARD, 400, 10, 49.99",
            "PREMIUM, 900, 25, 108.99",
            "UNLIMITED, 5000, 200, 99.99"
    })
    void shouldReturnPlan(Plan plan, int minutes, int gigabytes, double expectedMonthlyCost) {
        double cost = tariffCalculator.calculateMonthlyCost(plan, minutes, gigabytes);
        assertEquals(expectedMonthlyCost, cost);
    }

    @ParameterizedTest
    @EnumSource(Plan.class)
    void shouldReturnCostForEachPlan(Plan plan) {
        assertEquals(plan.getMonthlyPrice(), tariffCalculator.calculateMonthlyCost(plan, 0, 0));
    }

    //recommendedPlan
    @ParameterizedTest(name = "{0} minut, {1} GB -> plan {2}")
    @CsvSource({
            "50, 2, BASIC",
            "200, 5, STANDARD",
            "101, 3, STANDARD",
            "500, 25, PREMIUM",
            "1000, 50, UNLIMITED"
    })
    void shouldReturnRecommendedPlan(int neededMinutes, int neededGigabytes, String expectedPlan) {
        String recommendedPlan = tariffCalculator.recommendPlan(neededMinutes, neededGigabytes);

        assertEquals(expectedPlan, recommendedPlan);
    }

    //validatePhoneNumber
    @ParameterizedTest
    @ValueSource(strings = {
            "500123456",
            "601234567",
            "789012345",
            "880123456"
    })
    void shouldReturnTrueWhenValidateCorrectNumbers(String numbers) {
        assertTrue(tariffCalculator.validatePhoneNumber(numbers));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345",
            "1234567890",
            "abcdefghi",
            "123456789",
            "000000000"
    })
    void shouldReturnFalseWhenValidateWrongNumbers(String number) {
        assertFalse(tariffCalculator.validatePhoneNumber(number));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowIllegalArgumentWhenNull(String phoneNumber) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tariffCalculator.validatePhoneNumber(phoneNumber));
        assertEquals("Phone number cannot be null", ex.getMessage());

    }

    @Test
    void shouldThrowIllegalArgumentCalculatedMonthlyCostWhenPlanNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tariffCalculator.calculateMonthlyCost(null, 20, 20));

        assertEquals("Plan cannot be null", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentCalculatedMonthlyCostWhenMinutesAreNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tariffCalculator.calculateMonthlyCost(Plan.UNLIMITED, -300, 0));
        assertEquals("Usage cannot be negative", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentRecommendPlanWhenMinutesAreNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tariffCalculator.recommendPlan(-300, 20));
        assertEquals("Usage cannot be negative", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentRecommendPlanWhenGigabytesAreNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tariffCalculator.recommendPlan(300, -20));
        assertEquals("Usage cannot be negative", ex.getMessage());
    }
}