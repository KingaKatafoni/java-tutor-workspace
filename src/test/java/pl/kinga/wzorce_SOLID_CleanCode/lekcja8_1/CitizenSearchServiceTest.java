package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CitizenSearchServiceTest {
    private static CitizenRepository getCitizenRepository() {
        CitizenRepository citizenRepository = new CitizenRepository();
        Citizen citizen1 = new Citizen("12345678901", "Adam", "Malysz", 60, "Wisla");
        Citizen citizen2 = new Citizen("85030567890", "Jan", "Nowak", 55, "Krakow");
        Citizen citizen3 = new Citizen("01210198765", "Maria", "Wiszniewska", 25, "Warszawa");
        Citizen citizen4 = new Citizen("78050243210", "Piotr", "Zielinski", 48, "Krakow");
        Citizen citizen5 = new Citizen("95120654321", "Katarzyna", "Wozniak", 30, "Krakow");

        citizenRepository.save(citizen1);
        citizenRepository.save(citizen2);
        citizenRepository.save(citizen3);
        citizenRepository.save(citizen4);
        citizenRepository.save(citizen5);
        return citizenRepository;
    }

    @Test
    void shouldReturnFilteredWhenFindByCity() {
        CitizenRepository citizenRepository = getCitizenRepository();

        CitizenSearchService citizenSearchService = new CitizenSearchService(citizenRepository);

        List<Citizen> foundByCityWisla = citizenSearchService.findByCity("Wisla");
        List<Citizen> foundByCityKrakow = citizenSearchService.findByCity("Krakow");
        assertAll(
                () -> assertEquals(1, foundByCityWisla.size()),
                () -> assertEquals(3, foundByCityKrakow.size())
        );
    }

    @Test
    void shouldReturnSortedByLastNameListWhenFindByCity() {
        CitizenRepository citizenRepository = getCitizenRepository();

        CitizenSearchService citizenSearchService = new CitizenSearchService(citizenRepository);

        List<Citizen> foundByCityKrakow = citizenSearchService.findByCity("Krakow");
        assertAll(
                () -> assertEquals(3, foundByCityKrakow.size()),
                () -> assertEquals("Nowak", foundByCityKrakow.get(0).lastName()),
                () -> assertEquals("Wozniak", foundByCityKrakow.get(1).lastName()),
                () -> assertEquals("Zielinski", foundByCityKrakow.get(2).lastName())

        );
    }

    @Test
    void shouldFilterByAgeWhenFindSeniors() {
        CitizenRepository citizenRepository = getCitizenRepository();

        CitizenSearchService citizenSearchService = new CitizenSearchService(citizenRepository);

        List<Citizen> seniors = citizenSearchService.findSeniors(50);

        assertAll(
                () -> assertEquals(2, seniors.size()),
                () -> assertEquals("Malysz", seniors.get(0).lastName()),
                () -> assertEquals("Nowak", seniors.get(1).lastName())
        );
    }

}