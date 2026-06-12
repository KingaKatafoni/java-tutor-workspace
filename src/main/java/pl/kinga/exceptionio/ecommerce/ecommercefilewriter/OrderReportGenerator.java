package pl.kinga.exceptionio.ecommerce.ecommercefilewriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OrderReportGenerator {
    public static void generateReport(String inputPath, String outputPath) {
        double totalAmount;
        BigDecimal totalCompletedOrders = new BigDecimal("0.0");
        int completedCount = 0;
        List<String> orders = new ArrayList<>();
        List<String> outputLines = new ArrayList<>();

        try {
            orders = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
            return;
        }

        outputLines.add("=== Order Report ===");

        for (String order : orders) {
            String[] singleItem = order.split(";");
            if (singleItem.length < 5) continue;
            if (singleItem[4].contains("COMPLETED")) {
                totalAmount = Double.parseDouble(singleItem[2]) * Integer.parseInt(singleItem[3]);
                outputLines.add(singleItem[0] + ": " + singleItem[1] + " - " + String.format("%.2f", totalAmount));
                completedCount++;
                totalCompletedOrders = totalCompletedOrders.add(BigDecimal.valueOf(totalAmount));
            }
        }
        outputLines.add("----");
        outputLines.add("Total completed orders: " + completedCount);
        outputLines.add("Total value: " + totalCompletedOrders + " PLN");

        Path dir = Path.of(outputPath);
        try {
            Files.createDirectories(dir);
            Files.write(dir.resolve("order_report.txt"), outputLines);
        } catch (IOException e) {
            System.out.println("Cannot write report: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        generateReport("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/dane/orders.txt", "/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/output");
        System.out.println("Report saved to output/order_report.txt");
        System.out.println();
        List<String> lines = Files.readAllLines(Path.of("/Users/kinga/Documents/Projekty/Java_tutor/lekcje/04_wyjatki_io/08_pliki_pisanie/output/order_report.txt"));
        for (String line : lines) {
            System.out.println(line);
        }

    }
}
