package pl.kinga.exceptionio.publicsector.publicsectorfilereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CitizenFileReader {
    public static void readAndDisplay(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                String[] data = line.split(";");
                if (data.length < 3) continue;
                System.out.println("Citizen: " + data[0] + ", PESEL: " + data[1] + ", City: " + data[2]);
            }


        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static int countFromCity(String filePath, String city) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            int count = 0;
            for (String line : lines) {
                String[] data = line.split(";");
                if (data.length < 3) continue;
                if (data[2].equals(city)) {
                    count++;
                }
            }
            return count;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        CitizenFileReader.readAndDisplay("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/07_pliki_czytanie/dane/citizens.txt");
        System.out.println(CitizenFileReader.countFromCity("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/07_pliki_czytanie/dane/citizens.txt", "Poznan"));
        CitizenFileReader.readAndDisplay("dane/ghost.txt");

    }
}
