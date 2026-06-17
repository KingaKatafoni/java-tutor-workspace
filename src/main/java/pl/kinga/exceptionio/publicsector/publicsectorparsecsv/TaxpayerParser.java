package pl.kinga.exceptionio.publicsector.publicsectorparsecsv;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaxpayerParser {
    public static Taxpayer parseLine(String line, int lineNumber) {

        String[] col = line.split(",");

        if (col.length < 7) {
            System.out.println("Skipping line " + lineNumber + ": not enough columns");
            return null;
        }

        if (col[2].isEmpty()) {
            col[2] = "BRAK ADRESU";
        }

        try {
            BigDecimal annualIncome = (col[4].isEmpty() ? BigDecimal.ZERO : new BigDecimal(col[4]));
            BigDecimal taxPaid = col[5].isEmpty() ? BigDecimal.ZERO : new BigDecimal(col[5]);

            return new Taxpayer(col[0], col[1], col[2], col[3], annualIncome, taxPaid, col[6]);

        } catch (NumberFormatException e) {
            System.out.println("Skipping line " + lineNumber + ": " + e.getMessage());
            return null;
        }
    }

    public static List<Taxpayer> loadTaxpayers(String filePath) {
        List<Taxpayer> taxpayers = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            return new ArrayList<>();
        }

        for (int i = 1; i < lines.size(); i++) {
            Taxpayer taxpayer = parseLine(lines.get(i), i);
            if (taxpayer != null) {
                taxpayers.add(taxpayer);
            }
        }
        return taxpayers;
    }

    public static void printSummary(List<Taxpayer> taxpayers){
        int activeTaxpayersCount = 0;
        BigDecimal totalAnnualIncome = BigDecimal.ZERO;
        for (Taxpayer taxpayer : taxpayers){
            System.out.println(taxpayer);
            if (taxpayer.status().equals("ACTIVE")){
                activeTaxpayersCount++;
                totalAnnualIncome = totalAnnualIncome.add(taxpayer.annualIncome());

            }
        }
        System.out.println("Active taxpayers: " + activeTaxpayersCount);
        System.out.println("Total annual income active taxpayers: " + totalAnnualIncome);
    }

    public static void main(String[] args){
       List<Taxpayer> taxpayers =  loadTaxpayers("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/09_parsowanie_csv/dane/taxpayers.csv");
       printSummary(taxpayers);
    }
}
