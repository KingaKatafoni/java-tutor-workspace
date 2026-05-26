package pl.kinga.kolekcjegeneryki.publicsector.publicsectoradditional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DutySchedule {
    public static void main (String[] args){
        Map<String, List<String>> schedule = new LinkedHashMap<>();
        schedule.put("Monday", List.of("Kowalski", "Nowak"));
        schedule.put("Tuesday", List.of("Zielinska", "Kowalski", "Wisniewska"));
        schedule.put("Wednesday", List.of("Nowak"));

        for (Map.Entry<String, List<String>> entry : schedule.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        for (Map.Entry<String, List<String>> entry : schedule.entrySet()){
            for (String entryValue : entry.getValue()){
                System.out.println(entry.getKey() + " - " + entryValue);
            }
        }

        System.out.println("-----Amount of duty------");
        int total = 0;
        for (List<String> entry : schedule.values()){
            total += entry.size();
        }
        System.out.println("Total amount of duty: " + total);

    }
}
