package pl.kinga.wielowatkowosc.lekcja6_9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RegistrationCounter {
    private AtomicInteger atomCount = new AtomicInteger(0);
    private int normalCount;

    public void registerAtomic() {
        atomCount.incrementAndGet();
    }

    public void registerNormal() {
        normalCount++;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        RegistrationCounter registrationCounter = new RegistrationCounter();

        int amountOfTasks = 10000;

        for (int i = 0; i < amountOfTasks; i++) {
            executorService.submit(
                    registrationCounter::registerAtomic);
        }
        for (int i = 0; i < amountOfTasks; i++) {
            executorService.submit(
                    registrationCounter::registerNormal);
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

        System.out.println("Atomic count: " + registrationCounter.atomCount.get() + " (expected 10000)");
        System.out.println("Normal count: " + registrationCounter.normalCount + " (expected 10000)");

        // 5. Uruchom kilka razy. Zapisz obserwacje:
        //    - Czy atomic zawsze = 10000? -> atomic jest zawsze 10000
        //    - Czy normal zawsze = 10000? -> za kazdym razem inna wartosc ale nigdy 10000
        //Atomic count: 10000 (expected 10000)
        //Normal count: 9221 (expected 10000)

        // PYTANIE (odpowiedz w komentarzu):
        // Dlaczego AtomicInteger jest szybszy od synchronized
        // dla prostego licznika? Uzyj slowa "CAS" w odpowiedzi.
        //AtomicInteger jest szybszy dla prostego licznika poniewaz nie blokuje monitorem
        //calego fragmentu kodu(obiektu czy metody), bo uzywa na poziomie CPU CAS (Compare-And_Swap)
    }
}
