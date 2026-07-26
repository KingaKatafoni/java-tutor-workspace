package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationProcessorTest {
    @Test
    void shouldReturnConfirmationWhenCertificate() {
        Citizen citizen = new Citizen("Adam Malysz", "98076543552", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.CERTIFICATE, citizen, false);

        String info = applicationProcessor.processApplication();
        assertEquals("Wniosek: CERTIFICATE | Wnioskodawca: Adam Malysz (PESEL: 98076543552) | Wiek: 56 | Oplata: 17.0 PLN", info);
    }

    @Test
    void shouldReturnConfirmationWhenCertificateUrgent() {
        Citizen citizen = new Citizen("Adam Malysz", "98076543552", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.CERTIFICATE, citizen, true);

        String info = applicationProcessor.processApplication();
        assertEquals("Wniosek: CERTIFICATE | Wnioskodawca: Adam Malysz (PESEL: 98076543552) | Wiek: 56 | Oplata: 37.0 PLN [PILNY]", info);
    }

    @Test
    void shouldReturnConfirmationWhenRegistration() {
        Citizen citizen = new Citizen("Adam Malysz", "98076543552", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        String info = applicationProcessor.processApplication();
        assertEquals("Wniosek: RESIDENCE_REGISTRATION | Wnioskodawca: Adam Malysz (PESEL: 98076543552) | Wiek: 56 | Oplata: 0.0 PLN", info);
    }

    @Test
    void shouldThrowIllegalArgumentWhenNameIsEmpty() {
        Citizen citizen = new Citizen("", "98076543552", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                applicationProcessor::processApplication);
        assertEquals("Name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenNameIsNull() {
        Citizen citizen = new Citizen(null, "98076543552", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                applicationProcessor::processApplication);
        assertEquals("Name is required", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselTooShort() {
        Citizen citizen = new Citizen("Adam Malysz", "980765435", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                applicationProcessor::processApplication);
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPeselTooLong() {
        Citizen citizen = new Citizen("Adam Malysz", "9807654359088", 56);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                applicationProcessor::processApplication);
        assertEquals("PESEL must have 11 digits", ex.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenAgeBelow18() {
        Citizen citizen = new Citizen("Adam Malysz", "98076543512", 17);
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(ApplicationType.RESIDENCE_REGISTRATION, citizen, false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                applicationProcessor::processApplication);
        assertEquals("Citizen must be adult", ex.getMessage());
    }

}