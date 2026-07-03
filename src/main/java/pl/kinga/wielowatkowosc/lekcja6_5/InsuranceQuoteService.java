package pl.kinga.wielowatkowosc.lekcja6_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class InsuranceQuoteService {

    static double fetchQuoteFromProviderA(String policyType) {
        sleep(500);
        return 1200.00;
    }

    static double fetchQuoteFromProviderB(String policyType) {
        sleep(700);
        throw new RuntimeException("Provider B timeout");
    }

    static double fetchQuoteFromProviderC(String policyType) {
        sleep(400);
        return 980.50;
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String policyType = "HOME_INSURANCE";
        long start = System.nanoTime();

        // 1. Uruchom rownolegle 3 zapytania (supplyAsync):
        //    - fetchQuoteFromProviderA(policyType)
        //    - fetchQuoteFromProviderB(policyType) <-- ten rzuci wyjatek!
        //    - fetchQuoteFromProviderC(policyType)


        CompletableFuture<Double> futureFromProviderA = CompletableFuture
                .supplyAsync(() -> fetchQuoteFromProviderA(policyType));

        CompletableFuture<Double> futureFromProviderB = CompletableFuture
                .supplyAsync(() -> fetchQuoteFromProviderB(policyType));

        CompletableFuture<Double> futureFromProviderC = CompletableFuture
                .supplyAsync(() -> fetchQuoteFromProviderC(policyType));

        // 2. Dla KAZDEGO uzyj exceptionally:
        //    - jesli blad, wypisz "[ProviderX] Error: <message>"
        //    - i zwroc -1.0 jako wartosc domyslna

        futureFromProviderA = futureFromProviderA.exceptionally(ex -> {
            System.out.println("[Provider A] Error: " + "<" + ex.getMessage() + ">");
            return -1.0;
        });

        futureFromProviderB = futureFromProviderB.exceptionally(ex -> {
            System.out.println("[Provider B] Error: " + "<" + ex.getMessage() + ">");
            return -1.0;
        });

        futureFromProviderC = futureFromProviderC.exceptionally(ex -> {
            System.out.println("[Provider C] Error: " + "<" + ex.getMessage() + ">");
            return -1.0;
        });

        // 3. Poczekaj na wszystkie (join) i zbierz wyniki do listy
        List<Double> futuresValues = new ArrayList<>();

        futuresValues.add(futureFromProviderA.join());
        futuresValues.add(futureFromProviderB.join());
        futuresValues.add(futureFromProviderC.join());

        // 4. Wypisz kazda wycene:
        //    "Provider A: 1200.00 PLN"
        //    "Provider B: UNAVAILABLE"
        //    "Provider C: 980.50 PLN"
        //    (jesli wartosc == -1.0, wypisz UNAVAILABLE)

        String[] providers = {"A", "B", "C"};

        for (int i = 0; i<futuresValues.size(); i++){
            double value = futuresValues.get(i);
            if (value == -1.0){
                System.out.println("Provider " + providers[i] + ": UNAVAILABLE");
            } else {
                System.out.println("Provider " + providers[i] + ": " + value + " PLN");
            }
        }

        // 5. Oblicz srednia z DOSTEPNYCH wycen (ignoruj -1.0)
        //    i wypisz: "Average quote: XXX PLN"
        double sum = futuresValues.stream()
                .filter(r->!r.equals(-1.0))
                .reduce(0.0, Double::sum);
        long count = futuresValues.stream()
                .filter(r -> !r.equals(-1.0))
                .count();
        double average = sum/count;

        System.out.println("Average quote: " + average + " PLN");

        long elapsed = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time: " + elapsed + " ms");

        // PYTANIE (odpowiedz w komentarzu):
        // Co by sie stalo gdybys NIE dala exceptionally,
        // a Provider B rzucil wyjatek? Gdzie ten wyjatek "wyplynie"?
        // zrobilam taka symulacje przez przypadek :) bo nie przypisalam do zmiennej .exceptionally
        // wiec dostalam error w runtime i nic sie nie wykonalo oprocz throw new RuntimeException("Provider B timeout");
    }
}
