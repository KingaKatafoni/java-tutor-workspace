package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CitizenLogFormatterTest {
    CitizenLogFormatter logFormatter = new CitizenLogFormatter();
    Citizen citizen = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");

    @Test
    void shouldReturnLogWhenCitizenRegistered() {
        String log = logFormatter.formatLog(citizen);
        assertAll(
                () -> assertTrue(log.contains("Adam")),
                () -> assertTrue(log.contains("Malysz")),
                () -> assertTrue(log.contains("12345678901")),
                () -> assertTrue(log.contains("Wisla")),
                () -> assertTrue(log.contains("56"))
        );

    }
}