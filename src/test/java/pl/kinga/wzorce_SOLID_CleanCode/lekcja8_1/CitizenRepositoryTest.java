package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitizenRepositoryTest {

    @Test
    void shouldSaveWhenCitizenIsCorrect(){
        Citizen citizen = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");
        CitizenRepository citizenRepository = new CitizenRepository();

        citizenRepository.save(citizen);
        List<Citizen> citizens = citizenRepository.findAll();
        Citizen savedCitizen = citizens.get(0);

        assertAll(
                () -> assertEquals("12345678901", savedCitizen.pesel()),
                () -> assertEquals("Adam", savedCitizen.firstName()),
                () -> assertEquals("Malysz", savedCitizen.lastName()),
                () -> assertEquals(56, savedCitizen.age()),
                () -> assertEquals("Wisla", savedCitizen.city())
        );
    }

    @Test
    void shouldThrowIllegalStateWhenPeselDuplicated(){
        Citizen citizen1 = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");
        Citizen citizen2 = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");
        CitizenRepository citizenRepository = new CitizenRepository();

        citizenRepository.save(citizen1);
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> citizenRepository.save(citizen2));
        assertEquals("Citizen already registered: 12345678901", ex.getMessage());

    }

    @Test
    void shouldReturnCitizenWhenFindByPesel(){
        Citizen citizen1 = new Citizen("12345678901", "Adam", "Malysz", 56, "Wisla");
        Citizen citizen2 = new Citizen("12345678456", "Maria", "Bak", 6, "Konin");

        CitizenRepository citizenRepository = new CitizenRepository();
        citizenRepository.save(citizen1);
        citizenRepository.save(citizen2);

        Citizen citizenFoundByPesel1 = citizenRepository.findByPesel("12345678901");
        Citizen citizenFoundByPesel2 = citizenRepository.findByPesel("12345678456");

        assertAll(
                () -> assertEquals("12345678901", citizenFoundByPesel1.pesel()),
                () -> assertEquals("12345678456", citizenFoundByPesel2.pesel()),
                () -> assertEquals("Malysz", citizenFoundByPesel1.lastName()),
                () -> assertEquals("Bak", citizenFoundByPesel2.lastName())
        );





    }




}