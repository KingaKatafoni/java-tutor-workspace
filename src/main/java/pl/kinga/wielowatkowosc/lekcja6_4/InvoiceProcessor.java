package pl.kinga.wielowatkowosc.lekcja6_4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvoiceProcessor {
    record Invoice(String invoiceId, String supplier, BigDecimal amount, int processingTimeMs) {
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Invoice> invoices = List.of(
                new Invoice("INV001", "OfficeSupplies Ltd", new BigDecimal("2500.00"), 800),
                new Invoice("INV002", "CleaningPro", new BigDecimal("1200.00"), 600),
                new Invoice("INV003", "IT Solutions", new BigDecimal("45000.00"), 1500),
                new Invoice("INV004", "FurnitureCo", new BigDecimal("8900.00"), 900),
                new Invoice("INV005", "SecurityGuard Inc", new BigDecimal("3400.00"), 700),
                new Invoice("INV006", "PrintShop", new BigDecimal("600.00"), 400),
                new Invoice("INV007", "CateringService", new BigDecimal("2100.00"), 1100),
                new Invoice("INV008", "ElectricityProvider", new BigDecimal("15000.00"), 500)
        );

        // 1. Stworz Callable<String> dla kazdej faktury:
        //    - Thread.sleep(processingTimeMs)
        //    - zwraca: "INV001: OfficeSupplies Ltd — 2500.00 PLN [verified]"

        List<Callable<String>> callableList = new ArrayList<>();
        for (Invoice i : invoices) {
            Callable<String> invoiceCallable = () -> {
                Thread.sleep(i.processingTimeMs);
                return i.invoiceId + ": " + i.supplier + " - " + i.amount + " PLN [verified]";
            };
            callableList.add(invoiceCallable);
        }

        List<ExecutorService> executors = new ArrayList<>();
        // 2. Uzyj invokeAll() z newFixedThreadPool(3).
        //    Zmierz czas. Wypisz wyniki.


        ExecutorService executor = Executors.newFixedThreadPool(3);
        long startTime1 = System.nanoTime();
        List<Future<String>> tasks = executor.invokeAll(callableList);
        long endTime1 = System.nanoTime();
        executors.add(executor);

        // 3. Powtorz to samo z newFixedThreadPool(8).
        //    Zmierz czas. Wypisz wyniki.


        ExecutorService executor2 = Executors.newFixedThreadPool(8);
        long startTime2 = System.nanoTime();
        List<Future<String>> tasks2 = executor2.invokeAll(callableList);
        long endTime2 = System.nanoTime();
        executors.add(executor2);

        // 4. Powtorz z newCachedThreadPool().
        //    Zmierz czas.


        ExecutorService executor3 = Executors.newCachedThreadPool();
        long startTime3 = System.nanoTime();
        List<Future<String>> tasks3 = executor3.invokeAll(callableList);
        long endTime3 = System.nanoTime();
        executors.add(executor3);


        // 5. Wypisz porownanie czasow:

        System.out.println("Time newFixedThreadPool(3): " + (endTime1 - startTime1) / 1000000 + " ms");
        System.out.println("Time newFixedThreadPool(8): " + (endTime2 - startTime2) / 1000000 + " ms");
        System.out.println("Time newCachedThreadPool(): " + (endTime3 - startTime3) / 1000000 + " ms");

        // 6. Prawidlowe zamkniecie kazdego executora
        for (ExecutorService ex : executors){
            ex.shutdown();
            try {
                if (!ex.awaitTermination(10, TimeUnit.SECONDS)) {
                    ex.shutdownNow();
                }
            } catch (InterruptedException e) {
                ex.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        // PYTANIE (odpowiedz w komentarzu):
        // Dlaczego FixedPool(3) jest wolniejszy od FixedPool(8)?
        // FixedPool(3) jest wolniejsze poniewaz dzialamy na 3 watkach, reszta zadań (ktorych jest wiecej) czekaja az watek sie zwolni a w FixedPool(8) mamy 8 wolnych watkow czyli tyle ile jest zadan
        // Czy CachedPool jest szybszy od FixedPool(8)? Dlaczego?
        // CachedPool jest porownywalnie szybkie jak FixedPool(8) wydaje mi sie ze to dlatego ze cashed daje nam od 0 do nieskonczonosci watkow tutaj potzrebujemy 8 czyli tyle samo ile w fixed
    }
}
