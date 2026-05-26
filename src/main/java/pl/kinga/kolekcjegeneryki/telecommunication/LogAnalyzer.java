package pl.kinga.kolekcjegeneryki.telecommunication;

import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {
    private Map<String, Integer> eventCounts;

    public LogAnalyzer(){
        this.eventCounts = new HashMap<>();
    }

    public void logEvent(String eventType) {
       Integer amount = eventCounts.getOrDefault(eventType, 0);
       eventCounts.put(eventType, amount + 1);
    }

    public Integer getEventCount(String eventType){
        return eventCounts.getOrDefault(eventType, 0);
    }

    public String getMostFrequentEvent() {
        if (eventCounts.isEmpty()) {
            return null;
        }
        String bestEvent = "";
        int bestCount = 0;
        for (Map.Entry<String, Integer> entry : eventCounts.entrySet()) {
            if (entry.getValue() > bestCount) {
                bestCount = entry.getValue();
                bestEvent = entry.getKey();
            }
        }
        return bestEvent;
    }

    public void printReport(){
        System.out.println("-------Event | Amount---------");
        for (Map.Entry<String, Integer> entry : eventCounts.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }
    }

    public Integer getTotalEventCount() {
        int amount = 0;
        for (Integer value : eventCounts.values()) {
            amount += value;
        }
        return amount;
    }
}
