package pl.kinga.wielowatkowosc.lekcja6_4;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HealthCheckMonitor {
    public static void main(String[] args) throws InterruptedException {
        // Serwisy do monitorowania:
        // "PolicyService"   — czas odpowiedzi: 200ms
        // "ClaimsService"   — czas odpowiedzi: 500ms
        // "PaymentGateway"  — czas odpowiedzi: 300ms

        // 1. Stworz ScheduledExecutorService z pula 1 watku.
        long startTime = System.currentTimeMillis();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 2. Dla kazdego serwisu stworz Runnable ktory:

        Runnable task1 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + LocalTime.now() + "]" + "PolicyService - OK (200ms)");
        };

        Runnable task2 = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + LocalTime.now() + "]" + "ClaimsService - OK (500ms)");
        };

        Runnable task3 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + LocalTime.now() + "]" + "PaymentGateway - OK (300ms)");
        };

        // 3. Zaplanuj kazdy health check co 3 sekundy:
        //    scheduleAtFixedRate(..., 0, 3, TimeUnit.SECONDS)
        //    (0 = start natychmiast, 3 = powtarzaj co 3 sekundy)

        scheduler.scheduleAtFixedRate(task1, 0, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(task2, 0, 3, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(task3, 0, 3, TimeUnit.SECONDS);

        // 4. Pozwol monitorowi dzialac przez 10 sekund:
        //    Thread.sleep(10000);

        Thread.sleep(10000);

        // 5. Zamknij scheduler (shutdown + awaitTermination).

        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();

        // 6. Wypisz: "Monitoring stopped."
        System.out.println("Monitoring stopped");
        System.out.println("Total time: " + (endTime - startTime) + " ms");

        // PYTANIE (odpowiedz w komentarzu):
        // Stworzylas pule z 1 watkiem, ale masz 3 serwisy.
        // Czy wszystkie 3 health checki dzialaja "rownolegle"? // nie wydaje mi sie ze healthchecki nie działaja rownolegle tylko maja swoje czasy oczekiwania na zakonczenie poprzedniego zadania -> działaja sekwencyjnie
        // Co by sie stalo gdybys dala pule z 3 watkami? //
        // dla 3 watkow jest dzialanie rownolegle zamiast sekwencyjnego
        // dla 1 watku:
        //[14:01:43.133525]PolicyService - OK (200ms)
        //[14:01:43.639633]ClaimsService - OK (500ms)
        //[14:01:43.944505]PaymentGateway - OK (300ms)

        //dla 3 watkow
        //[14:02:47.659308]PolicyService - OK (200ms)
        //[14:02:47.748455]PaymentGateway - OK (300ms)
        //[14:02:47.951734]ClaimsService - OK (500ms)
    }
}
