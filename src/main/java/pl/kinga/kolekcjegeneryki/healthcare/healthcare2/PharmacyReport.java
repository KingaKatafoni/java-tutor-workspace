package pl.kinga.kolekcjegeneryki.healthcare.healthcare2;

import java.util.HashMap;
import java.util.Map;

public class PharmacyReport {

    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("Paracetamol", 120);
        stock.put("Ibuprofen", 85);
        stock.put("Amoxicillin", 30);
        stock.put("Metformin", 200);
        stock.put("Omeprazole", 15);

        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " szt.");
        }

        for (String name : stock.keySet()) {
            System.out.println("Medicine: " + name);
        }

        int amount = 0;
        for (Integer entry : stock.values()) {
            amount += entry;
        }
        System.out.println("Total amount of medicines: " + amount + " szt.");

        System.out.println("Low stock medicament: ");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 50) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " szt.");
            }


        }
    }
}
