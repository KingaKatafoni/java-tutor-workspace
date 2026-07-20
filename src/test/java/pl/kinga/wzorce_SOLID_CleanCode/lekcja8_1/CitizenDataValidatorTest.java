package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CitizenDataValidatorTest {
    CitizenDataValidator citizenDataValidator = new CitizenDataValidator();

    @Test
    void shouldNotThrowIllegalArgumentWhenPeselIsCorrect() {
        assertDoesNotThrow(
                () -> citizenDataValidator.validatePesel("12345678901"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123",
            "123456789111",
    })
    void shouldThrowIllegalArgumentWhenPeselHasIncorrectLength(String pesel) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validatePesel(pesel));
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validatePesel(null));
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselConsistsOfNotOnlyDigits() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validatePesel("er490932198"));
        assertEquals("PESEL must contain only digits", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0,
            150
    })
    void shouldNotThrowIllegalWhenAgeIsEqualsLimits(int age) {
        assertDoesNotThrow(() -> citizenDataValidator.validateAge(age));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1,
            151
    })
    void shouldThrowIllegalArgumentWhenAgeIsOutOfRange(int age) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validateAge(age));
        assertEquals("Age must be between 0 and 150", ex.getMessage());
    }

  @Test
    void shouldThrowIllegalArgumentWhenFirstNameIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validateFirstName(null));
        assertEquals("First name is mandatory", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenFirstNameIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validateFirstName(""));
        assertEquals("First name is mandatory", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenLastNameIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validateLastName(null));
        assertEquals("Last name is mandatory", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenLastNameIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenDataValidator.validateLastName(""));
        assertEquals("Last name is mandatory", ex.getMessage());
    }



}