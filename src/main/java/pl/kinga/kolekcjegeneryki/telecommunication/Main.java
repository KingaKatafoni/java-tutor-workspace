package pl.kinga.kolekcjegeneryki.telecommunication;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer();
        for (String event : List.of( "LOGIN", "LOGIN", "LOGOUT", "LOGIN", "ERROR", "ERROR", "TIMEOUT", "LOGIN", "LOGOUT",
                "ERROR", "LOGIN", "TIMEOUT", "LOGOUT", "LOGIN", "ERROR")) {
                analyzer.logEvent(event);
        }

        analyzer.printReport();
        System.out.println("The most frequent event: " + analyzer.getMostFrequentEvent());
        System.out.println("Total amount of events: " + analyzer.getTotalEventCount());
        System.out.println("Event \"ERROR\" occurs " + analyzer.getEventCount("ERROR") + " times");

    }
}
