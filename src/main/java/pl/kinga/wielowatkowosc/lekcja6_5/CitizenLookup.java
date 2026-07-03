package pl.kinga.wielowatkowosc.lekcja6_5;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

public class CitizenLookup {
    static String fetchPersonalData(String pesel) {
        sleep(600);
        return pesel + ": Jan Kowalski, ur. 1992-01-01";
    }

    static String fetchAddress(String pesel) {
        sleep(400);
        return "ul. Polna 5, 00-001 Warszawa";
    }

    static String fetchTaxStatus(String pesel) {
        sleep(500);
        return "Tax status: NO_DEBT";
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        String pesel = "92010112345";
        long start = System.nanoTime();

        // 1. Uzyj supplyAsync, zeby uruchomic ROWNOLEGLE:
        //    - fetchPersonalData(pesel)
        //    - fetchAddress(pesel)
        //    - fetchTaxStatus(pesel)

        CompletableFuture<String> futurePersonalData = CompletableFuture.supplyAsync(() -> fetchPersonalData(pesel));

        CompletableFuture<String> futureAddress = CompletableFuture.supplyAsync(() -> fetchAddress(pesel));

        CompletableFuture<String> futureTaxStatus = CompletableFuture.supplyAsync(() -> fetchTaxStatus(pesel));

        // 2. Na wyniku fetchPersonalData uzyj thenApply:
        //    zamien wynik na UPPERCASE

       futurePersonalData = futurePersonalData.thenApply(String::toUpperCase);

        // 3. Na wyniku fetchTaxStatus uzyj thenApply:
        //    dodaj na koncu " [VERIFIED]"
        futureTaxStatus = futureTaxStatus.thenApply(r -> r + " [VERIFIED]");

        // 4. Polacz WSZYSTKIE 3 wyniki w jeden String:
        //    uzyj thenCombine — najpierw polacz 2 wyniki,
        //    potem polacz ten rezultat z trzecim.
        //    Format: kazdy wynik w nowej linii
        CompletableFuture<String> personalDataAddressCombined = futurePersonalData
                .thenCombine(futureAddress, (p,a) -> p + " zamieszkały " + a);

        CompletableFuture<String> allCombined = personalDataAddressCombined
                .thenCombine(futureTaxStatus, (pa, t) -> pa + " " + t);

        // 5. Na polaczonym wyniku uzyj thenAccept:
        //    wypisz "=== Citizen Report ===" i pod spodem wynik
        allCombined
                .thenAccept(c -> {
                    System.out.println("=== Citizen Report ===");
                    System.out.println(c);
                });

        // 6. Wywolaj join() zeby poczekac na zakonczenie
        allCombined.join();

        long elapsed = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Time: " + elapsed + " ms");

        // PYTANIE (odpowiedz w komentarzu):
        // Ile czasu POWINNO to zajac? ~600ms, ~1000ms, ~1500ms?
        // Dlaczego?
        //Powinno to zając ok 600ms poniewaz mamy do czynienia ze wspoldzielona pula watkow wiec zadania nie czekaja na siebie nie sa sekwencyjne
        // dodatkowo łancuchowanie z then nie blokuje nam watku main
    }
}
