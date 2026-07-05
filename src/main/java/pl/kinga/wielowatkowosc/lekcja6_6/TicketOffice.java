package pl.kinga.wielowatkowosc.lekcja6_6;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TicketOffice {
    private static int availableTickets = 50;
    private static int soldTickets = 0;

    // 1. Napisz metode sellTicket(String buyerName):
    //    - jesli availableTickets > 0:
    //        Thread.sleep(10) — symulacja przetwarzania
    //        availableTickets--
    //        soldTickets++
    //        wypisz: "[BuyerName] Ticket sold! Remaining: X"
    //    - jesli brak biletow:
    //        wypisz: "[BuyerName] SOLD OUT!"
    //
    //    UWAGA: metoda NIE JEST jeszcze synchronized.

    public static synchronized void sellTicket(String buyerName) throws InterruptedException {
        if (availableTickets > 0) {
            Thread.sleep(10);
            availableTickets--;
            soldTickets++;
            System.out.println("[" + buyerName + "] Ticket sold! Remaining: " + availableTickets);
        } else {
            System.out.println("[" + buyerName + "] SOLD OUT!");
        }
    }

    public static void main(String[] args) {
        // 2. W main():
        //    - Stworz ExecutorService z newFixedThreadPool(10)
        //    - Wyslij 70 zadan (submit), kazde wywoluje sellTicket("Buyer-1" ... "Buyer-70")
        //    - Zamknij executor (shutdown + awaitTermination 30 sekund)
        //    - Wypisz podsumowanie:
        //      "Sold: X"
        //      "Remaining: X"
        //      "Total (sold + remaining): X"  ← powinno byc 50!

        ExecutorService executor = Executors.newFixedThreadPool(10);
        String[] names = new String[70];
        for (int i = 0; i < names.length; i++) {
            names[i] = "Buyer-" + (i + 1);
        }

        Arrays.stream(names)
                .forEach(b -> {
                    executor.submit(() -> {
                        try {
                            sellTicket(b);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("=== Summary ===");
        System.out.println("Sold: " + soldTickets);
        System.out.println("Remaining: " + availableTickets);
        System.out.println("Total (sold + remaining) " + (soldTickets + availableTickets));


        // 3. Uruchom KILKA RAZY bez synchronized.
        //    Czy total zawsze wynosi 50? Zapisz obserwacje w komentarzu.

        // podczas uruchamiania mam sold 64 remaining -9, i konsola wyglada nastepujaco:
        //[Buyer-6] Ticket sold! Remaining: 47 -> tu jest taki sam remaining jak dla buyer 8
        //[Buyer-6] SOLD OUT!
        //[Buyer-8] Ticket sold! Remaining: 47

        // 4. Dodaj synchronized do sellTicket i uruchom ponownie.
        //    Czy teraz total zawsze wynosi 50?
        // teraz total zawsze wynosi 50
        // === Summary ===
        //Sold: 50
        //Remaining: 0
        //Total (sold + remaining) 50

        // PYTANIE (odpowiedz w komentarzu):
        // Dlaczego sam "if (availableTickets > 0)" nie wystarczy
        // bez synchronized? Co moze sie stac?
        // mamy sytuacje jak z count() z lekcji.
        // Na kilku wątkach jednocześnie może być wywyoływana metoda sellTicket() , ktora inkrementuje zmienna soldTickets i dekrementuje availableTickets
        // i te zmienne moga zle updatowac wartosci tzn dwie osoby kupuja bilet a update wartosci jest pozniej i nie bierze pod uwage już zaistnialej zmiany,
        // stąd total był za kazdym razem inny i rozny od 50.
        //Synchronized dzieki monitor blokuje/zamyka watek i reszta czeka.
    }


}
