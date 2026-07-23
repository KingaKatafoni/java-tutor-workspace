package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    @Test
    void shouldReturnPatientWhenRequiredFieldsOnly() {
        Patient patient = new Patient.Builder("Anna", "Nowak", "90876543321").build();

        assertAll(
                () -> assertEquals("Anna", patient.getFirstName()),
                () -> assertEquals("Nowak", patient.getLastName()),
                () -> assertEquals("90876543321", patient.getPesel()),
                () -> assertNull(patient.getAllergies())
        );
    }

    @Test
    void shouldReturnValueWhenChainingAllFields() {
        Patient patient = new Patient.Builder("Anna", "Nowak", "90876543321")
                .phoneNumber("668908765")
                .email("karo@gmil.com")
                .city("Krakow")
                .bloodType("ARh+")
                .allergies("penicillin")
                .emergencyContact("908786546")
                .build();

        assertAll(
                () -> assertEquals("Anna", patient.getFirstName()),
                () -> assertEquals("Nowak", patient.getLastName()),
                () -> assertEquals("90876543321", patient.getPesel()),
                () -> assertEquals("668908765", patient.getPhoneNumber()),
                () -> assertEquals("karo@gmil.com", patient.getEmail()),
                () -> assertEquals("Krakow", patient.getCity()),
                () -> assertEquals("ARh+", patient.getBloodType()),
                () -> assertEquals("penicillin", patient.getAllergies()),
                () -> assertEquals("908786546", patient.getEmergencyContact())

        );
    }

    @Test
    void shouldReturnValueWhenChainingOptional() {
        Patient patient = new Patient.Builder("Anna", "Nowak", "90876543321")
                .phoneNumber("668908765")
                .email("karo@gmil.com")
                .city("Krakow")
                .bloodType("ARh+")
                .build();

        assertAll(
                () -> assertEquals("Anna", patient.getFirstName()),
                () -> assertEquals("Nowak", patient.getLastName()),
                () -> assertEquals("90876543321", patient.getPesel()),
                () -> assertEquals("668908765", patient.getPhoneNumber()),
                () -> assertEquals("karo@gmil.com", patient.getEmail()),
                () -> assertEquals("Krakow", patient.getCity()),
                () -> assertEquals("ARh+", patient.getBloodType()),
                () -> assertNull(patient.getAllergies()),
                () -> assertNull(patient.getEmergencyContact())
        );
    }

    @Test
    void shouldThrowIllegalStateWhenFirstNameNull() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder(null, "Nowak", "90876543321")
                        .build());
        assertEquals("First name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenFirstNameIsEmpty() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("", "Nowak", "90876543321")
                        .build());
        assertEquals("First name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenLastNameNull() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("Anna", null, "90876543321")
                        .build());
        assertEquals("Last name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenLastNameIsEmpty() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("Anna", "", "90876543321")
                        .build());
        assertEquals("Last name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenPeselNull() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("Anna", "Nowak", null)
                        .build());
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenPeselToShort() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("Anna", "Nowak", "123")
                        .build());
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalStateWhenPeselToLong() {
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> new Patient.Builder("Anna", "Nowak", "1234567890123")
                        .build());
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }


}