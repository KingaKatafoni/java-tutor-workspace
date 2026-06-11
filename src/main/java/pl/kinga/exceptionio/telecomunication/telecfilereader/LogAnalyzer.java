package pl.kinga.exceptionio.telecomunication.telecfilereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogAnalyzer {
    static void analyzeLog(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            int infoCount = 0;
            int warnCount = 0;
            int errorCount = 0;
            System.out.println("=== Log Analysis ===");
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR")) {
                    errorCount++;
                } else if(line.contains("WARN")){
                    warnCount++;
                } else if(line.contains("INFO")){
                    infoCount++;
                }
            }
            System.out.println("INFO: " + infoCount);
            System.out.println("WARN: " + warnCount);
            System.out.println("ERROR: " + errorCount);
            System.out.println("Total: " + (infoCount + warnCount + errorCount));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        analyzeLog("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/07_pliki_czytanie/dane/server_log.txt");
        analyzeLog("dane/nonexistent.txt");
    }
}
