package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenRegistrationServiceTest {
    @Mock
    CitizenRepository citizenRepository;

    @Mock
    CitizenDataValidator dataValidator;

    @Mock
    CitizenLogFormatter logFormatter;

    @Mock
    ConfirmationFormatter confirmationFormatter;

    @Mock
    CitizenIdGenerator idGenerator;

    @InjectMocks
    CitizenRegistrationService citizenRegistrationService;

    @Test
    void shouldVerifyValidatorRepositoryFormatterWhenCorrectRegistration() {
        Citizen citizen = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");
        when(idGenerator.generateId("12345678901")).thenReturn("CIT-1234-5678");
        citizenRegistrationService.registerCitizen("12345678901", "Adam", "Malysz", 56, "Wisla");

        verify(dataValidator).validatePesel("12345678901");
        verify(dataValidator).validateAge(56);
        verify(citizenRepository).save(citizen);
        verify(idGenerator).generateId("12345678901");
        verify(confirmationFormatter).formatConfirmation(citizen, "CIT-1234-5678");

    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselIncorrect() {

        doThrow(new IllegalArgumentException("PESEL must have 11 digits")).when(dataValidator).validatePesel("123");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> citizenRegistrationService.registerCitizen("123", "Adam", "Malysz", 56, "Wisla"));
        assertEquals("PESEL must have 11 digits", ex.getMessage());
        verify(citizenRepository, never()).save(any(Citizen.class));
    }

    @Test
    void shouldThrowIllegalStateWhenDuplicate() {
        doThrow(new IllegalStateException("Citizen already registered: 12345678901")).when(citizenRepository).save(new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla"));

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> citizenRegistrationService.registerCitizen("12345678901", "Adam", "Malysz", 56, "Wisla"));
        assertEquals("Citizen already registered: 12345678901", ex.getMessage());
    }


}