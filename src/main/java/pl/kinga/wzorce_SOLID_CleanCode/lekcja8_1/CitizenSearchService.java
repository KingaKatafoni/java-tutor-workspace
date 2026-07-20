package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import java.util.Comparator;
import java.util.List;

public class CitizenSearchService {
    private final CitizenRepository citizenRepository;

    public CitizenSearchService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public List<Citizen> findByCity(String city) {
        return citizenRepository.findAll().stream()
                .filter(c -> c.city().equals(city))
                .sorted(Comparator.comparing(Citizen::lastName))
                .toList();
    }

    public List<Citizen> findSeniors(int minAge) {
        return citizenRepository.findAll().stream()
                .filter(c -> c.age() >= minAge)
                .sorted(Comparator.comparing(Citizen::age).reversed())
                .toList();
    }

}
