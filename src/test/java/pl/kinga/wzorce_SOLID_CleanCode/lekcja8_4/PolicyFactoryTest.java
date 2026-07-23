package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolicyFactoryTest {
    @Test
    void shouldReturnInstanceCarPolicyWhenCreateCar() {
        Policy policy = PolicyFactory.create("CAR", "POL-001", "Adam Mickiewicz", 3);
        assertInstanceOf(CarPolicy.class, policy);
    }

    @Test
    void shouldReturnInstanceHomePolicyWhenCreateHome() {
        Policy policy = PolicyFactory.create("HOME", "POL-001", "Adam Mickiewicz", 67);
        assertInstanceOf(HomePolicy.class, policy);
    }

    @Test
    void shouldReturnInstanceTravelPolicyWhenCreateTravel() {
        Policy policy = PolicyFactory.create("TRAVEL", "POL-001", "Adam Mickiewicz", 14);
        assertInstanceOf(TravelPolicy.class, policy);
    }

    @Test
    void shouldThrowIllegalArgumentWhenUnknownPolicy() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> PolicyFactory.create("FLAT", "POL-001", "Adam Mickiewicz", 3)
        );
        assertEquals("Unknown policy type: FLAT", ex.getMessage());
    }

}