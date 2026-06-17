package pl.kinga.exceptionio.finalproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Resident> residents = ResidentImporter.importResidents("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/10_mini_projekt_import_csv/dane/residents_import.csv", "/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/10_mini_projekt_import_csv/output");

        System.out.println("Imported " + residents.size() + " residents. ");

        List<String> lines = new ArrayList<>();
                try {
                    lines = Files.readAllLines(Path.of("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/10_mini_projekt_import_csv/output/resident_import_raport.txt"));
                } catch (IOException e){
                    System.out.println("Cannot read file " + e.getMessage());
                }

                for (String line : lines){
                    System.out.println(line);
                }
    }
}
