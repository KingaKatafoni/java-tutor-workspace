package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitizenReportServiceTest {
    @Mock
    CitizenRepository citizenRepository;

    @InjectMocks
    CitizenReportService reportService;

    List<Citizen> citizens = List.of(
            new Citizen("92010112345", "Anna", "Kowalska", 34, "Warszawa"),
            new Citizen("85030567890", "Jan", "Nowak", 41, "Krakow"),
            new Citizen("01210198765", "Maria", "Wiszniewska", 25, "Warszawa"),
            new Citizen("78050243210", "Piotr", "Zielinski", 48, "Gdansk"),
            new Citizen("95120654321", "Katarzyna", "Wozniak", 30, "Krakow"));


    @Test
    void shouldReturn2WhenCountByCityGetWarszawa() {
        when(citizenRepository.findAll()).thenReturn(citizens);

        Map<String, Long> citizensCountByCity = reportService.countByCity();
        Long warszawa = citizensCountByCity.get("Warszawa");

        assertAll(
                () -> assertEquals(3, citizensCountByCity.size()),
                () -> assertEquals(2, warszawa)
        );


    }

    @Test
    void shouldReturn356WhenAverageAge() {
        when(citizenRepository.findAll()).thenReturn(citizens);
        citizenRepository.findAll();
        double v = reportService.averageAge();

        assertEquals(35.6, v);
    }


}