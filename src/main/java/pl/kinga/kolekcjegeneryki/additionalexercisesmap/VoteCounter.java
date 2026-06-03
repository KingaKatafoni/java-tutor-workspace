package pl.kinga.kolekcjegeneryki.additionalexercisesmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCounter {
    public static void main(String[] args) {
        List<String> votes = List.of(
                "Kowalski", "Nowak", "Kowalski", "Wisniewska",
                "Nowak", "Kowalski", "Wisniewska", "Nowak",
                "Kowalski", "Wisniewska", "Wisniewska", "Nowak", "Nowak"
        );

        Map<String, Integer> counter = new HashMap<>();

        for (String item : votes) {
            counter.put(item, counter.getOrDefault(item, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " glosy");
        }


        String winner = "";
        Integer maxValue = 0;
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                winner = entry.getKey();
            }
        }
        System.out.println("Winner:" + winner + ": " + maxValue + " glosow");
    }
}
