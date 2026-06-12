package pl.kinga.exceptionio.healthcare.healthcarefilewriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class VisitLogger {
    public static void createDailyLog(String inputPath, String outputPath, String date) {
        int counter = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            return;
        }

        try {
            Files.createDirectories(Path.of(outputPath).getParent());
        } catch (IOException e) {
            System.out.println("Cannot create directory: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputPath))) {
            writer.write("=== Daily Visit Log: " + date + " ===");
            writer.newLine();
            for (String line : lines) {
                String[] singleData = line.split(";");
                if (singleData.length < 5) continue;
                if (singleData[2].contains(date)) {
                    counter++;
                    writer.write(counter + ". " + singleData[0] + " (" + singleData[1] + ") - " + singleData[3] + " - " + singleData[4]);
                    writer.newLine();
                }
            }
            writer.write("---");
            writer.newLine();
            writer.write("Total visits: " + counter);
        } catch (IOException e) {
            System.out.println("Cannot write log: " + e.getMessage());
        }

    }

    public static void appendVisit(String outputPath, String visitLine) {
        try {
            Files.writeString(Path.of(outputPath), visitLine, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("File" + e.getMessage() + "does not exist!");
        }

    }

    public static void main(String[] args) {
        createDailyLog("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/dane/patients.txt", "/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/output/daily_log.txt", "2026-06-11");
        appendVisit("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/output/daily_log.txt", "\n9. Nowy Pacjent (00000000000) — Dr. Nowak — Wizyta doraźna");

        try {
            for (String line : Files.readAllLines(Path.of("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/output/daily_log.txt"))) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No such file!");
        }
    }
}
