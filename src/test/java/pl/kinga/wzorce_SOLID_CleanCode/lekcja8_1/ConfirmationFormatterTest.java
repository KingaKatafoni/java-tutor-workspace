package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfirmationFormatterTest {
    @Test
    void shouldContainsFormatterGeneratedId() {
        Citizen citizen = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");

        ConfirmationFormatter confirmationFormatter = new ConfirmationFormatter();

        String formatConfirmation = confirmationFormatter.formatConfirmation(citizen, "CIT-1234-3456");

        assertTrue(formatConfirmation.contains("Numer identyfikacyjny: CIT-1234-3456"));

    }


}