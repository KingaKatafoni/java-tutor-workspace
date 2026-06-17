package pl.kinga.exceptionio.finalproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResidentImporter {

    public static List<Resident> importResidents(String inputPath, String reportPath) {
        List<String> lines;
        List<Resident> residents = new ArrayList<>();
        List<String> reportLines = new ArrayList<>();
        List<String> errorLines = new ArrayList<>();
        List<String> successfullyImportedLines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Cannot read file " + e.getMessage());
            return new ArrayList<>();
        }

        reportLines.add("=== Resident Import Report ===");
        reportLines.add("Date: " + LocalDate.now());
        reportLines.add("Source: ");
        reportLines.add("\n--- Successfully imported ---");

        for (int i = 1; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(",");


            try {
                ResidentValidator.validate(columns, i);
                if (columns[5].isEmpty()) {
                    columns[5] = "N/A";
                }
                residents.add(new Resident(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6]));
                successfullyImportedLines.add(columns[1] + " " + columns[2] + "(" + columns[0] + ") - " + columns[4] + " - " + columns[6]);
            } catch (InvalidResidentDataException e) {
                System.out.println(e.getMessage());
                errorLines.add(e.getMessage());
            }

        }

        for (int i = 0; i < successfullyImportedLines.size(); i++) {
            reportLines.add((i + 1) + ". " + successfullyImportedLines.get(i));
        }

        reportLines.add("\n--- Errors ---");

        for (int i = 0; i < errorLines.size(); i++) {
            reportLines.add((i + 1) + ". " + errorLines.get(i));
        }

        reportLines.add("\n--- Summary ---");
        reportLines.add("Total lines processed: " + (lines.size() - 1));
        reportLines.add("Successfully imported: " + successfullyImportedLines.size());
        reportLines.add("Errors: " + errorLines.size());

        Path dir = Path.of(reportPath);

        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            System.out.println("Cannot create directory " + e.getMessage());
        }

        try {
            Files.write(dir.resolve("resident_import_raport.txt"), reportLines);
        } catch (IOException e) {
            System.out.println("Cannot write report " + e.getMessage());
        }

        return residents;
    }
}
