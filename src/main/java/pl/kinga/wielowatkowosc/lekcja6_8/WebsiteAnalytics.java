package pl.kinga.wielowatkowosc.lekcja6_8;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebsiteAnalytics {
    private ConcurrentHashMap<String, Integer> visits = new ConcurrentHashMap<>();
    //private HashMap<String, Integer> visits = new HashMap<>();

    public void recordVisit(String page) {
        visits.merge(page, 1, Integer::sum);

        int total = visits.values().stream().mapToInt(Integer::intValue)
                .sum();
        System.out.println("[" + Thread.currentThread().getName() + "]" + " Visit: " + page + " (total: " + total + ")");
    }

    public void generateReport() {
        System.out.println("=== Analytics Report ===");
        visits.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " visits"));

        visits.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> {
                    System.out.println("Most Popular: " + e.getKey());
                    System.out.println("Total: " + e.getValue() + " visits");
                });


    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        WebsiteAnalytics websiteAnalytics = new WebsiteAnalytics();

        String[] pages = {"/home", "/products", "/cart", "/checkout", "/account"};
        int amountOfVisits = 500;
        Random random = new Random();
        for (int i = 0; i < amountOfVisits; i++) {
            String page = pages[random.nextInt(pages.length)];
            executorService.submit(() -> websiteAnalytics.recordVisit(page));
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }

        websiteAnalytics.generateReport();
    }


    // PYTANIE (odpowiedz w komentarzu):
    // Co by sie stalo gdybys uzyla zwyklej HashMap
    // i 50 watkow zamiast 5? Czy wynik zawsze byłby poprawny?
    // po zmianie na HashMap i zmianie na 50 watkow wynik nie jest poprawny total jest rozny od 500
    //=== Analytics Report ===
    //account: 97 visits
    //home: 99 visits
    //cart: 102 visits
    //checkout: 102 visits
    //products: 98 visits
    //Most Popular: /cart
    //Total visits: 498
}
