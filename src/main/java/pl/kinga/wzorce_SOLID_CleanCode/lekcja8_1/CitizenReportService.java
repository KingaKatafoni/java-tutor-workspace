package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import java.util.Map;
import java.util.stream.Collectors;

public class CitizenReportService {
    private final CitizenRepository repository;

    public CitizenReportService(CitizenRepository repository) {
        this.repository = repository;
    }

    public Map<String, Long> countByCity() {

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Citizen::city,
                        Collectors.counting()
                ));
    }

    public double averageAge() {
        return repository.findAll().stream()
                .mapToInt(Citizen::age)
                .average()
                .orElse(0.0);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();

        report.append("=== Citizen Report ===\n");
        report.append("Total citizens: ")
                .append(repository.findAll().size())
                .append("\n");

        report.append("Average age: ")
                .append(String.format("%.1f", averageAge()))
                .append("\n");

        report.append("\nBy city:\n");
        countByCity().forEach(
                (city, count) -> report.append(" ")
                        .append(city)
                        .append(": ")
                        .append(count)
                        .append("\n")

        );
        return report.toString();
    }

}
