package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CitizenIdGeneratorTest {
    CitizenIdGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new CitizenIdGenerator();
    }

    @Test
    void shouldGenerateIdStartingWithCitAndPeselPrefix() {
        String id = generator.generateId("12345678901");
        assertAll(
                () -> assertTrue(id.startsWith("CIT-1234-")),
                () -> assertTrue(id.matches("CIT-\\d{4}-\\d{1,4}"))
        );


    }
}