package pl.kinga.testowanie.lekcja7_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenValidatorTest {
    //validatePesel()

    @Test
    void shouldReturnTrueWhenCorrectPesel() {
        CitizenValidator validator = new CitizenValidator();
        boolean condition = validator.validatePesel("92010112345");
        assertTrue(condition);
    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselIsNull() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validatePesel(null));
        assertEquals("PESEL cannot be null", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWithMessageWhenPeselIsTooShort() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validatePesel("123"));

        assertEquals("PESEL must be 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWithMessageWhenPeselTooLong() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validatePesel("123456789012"));
        assertEquals("PESEL must be 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWithMessageWhenPeselContainsNotOnlyDigits() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validatePesel("9201011234a"));
        assertEquals("PESEL must contain only digits", ex.getMessage());
    }

    //validateAge()

    @Test
    void shouldReturnAdultWhenAgeIs25() {
        CitizenValidator validator = new CitizenValidator();
        String result = validator.validateAge(25);
        assertEquals("ADULT", result);
    }

    @Test
    void shouldReturnAdultWhenAgeIs18() {
        CitizenValidator validator = new CitizenValidator();
        String result = validator.validateAge(18);
        assertEquals("ADULT", result);
    }

    @Test
    void shouldReturnAdultWhenAgeIs150() {
        CitizenValidator validator = new CitizenValidator();
        String result = validator.validateAge(150);
        assertEquals("ADULT", result);
    }

    @Test
    void shouldReturnMinorWhenAgeIs17() {
        CitizenValidator validator = new CitizenValidator();
        String result = validator.validateAge(17);
        assertEquals("MINOR", result);
    }

    @Test
    void shouldReturnMinorWhenAgeIs0() {
        CitizenValidator validator = new CitizenValidator();
        String result = validator.validateAge(0);
        assertEquals("MINOR", result);
    }

    @Test
    void shouldThrowIllegalArgumentWhenAgeIsNegative() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAge(-1));
        assertEquals("Age cannot be negative", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAgeExceedsMax() {
        CitizenValidator validator = new CitizenValidator();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAge(151));
        assertEquals("Age exceeds maximum", ex.getMessage());
    }

    //formatFullName()

    @Test
    void shouldReturnFormatNamesWhenNamesAreLowerCase() {
        CitizenValidator validator = new CitizenValidator();
        String name = validator.formatFullName("jan", "kowalski");
        assertEquals("KOWALSKI Jan", name);
    }

    @Test
    void shouldReturnFormatNamesWhenNamesAreUpperCase() {
        CitizenValidator validator = new CitizenValidator();
        String name = validator.formatFullName("ANNA", "NOWAK");
        assertEquals("NOWAK Anna", name);
    }

    @Test
    void shouldThrowIllegalArgumentWhenNameIsNullOrEmpty() {
        CitizenValidator validator = new CitizenValidator();
        assertAll(
                () -> assertEquals("Name cannot be null",
                        assertThrows(IllegalArgumentException.class,
                                () -> validator.formatFullName(null, "kowalski")).getMessage()),
                () -> assertEquals("Name cannot be null",
                        assertThrows(IllegalArgumentException.class,
                                () -> validator.formatFullName("jan", null)).getMessage()),
                () -> assertEquals("Name cannot be empty",
                        assertThrows(IllegalArgumentException.class,
                                () -> validator.formatFullName("", "kowalski")).getMessage())
        );
    }

}