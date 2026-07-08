package pl.kinga.wielowatkowosc.lekcja6_9;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {
    //Sklep internetowy generuje unikalne numery zamowien.
    // Wiele watkow tworzy zamowienia jednoczesnie — numer musi byc unikalny.

    private AtomicInteger nextOrderNumber = new AtomicInteger(1000);

    public String generateOrderNumber() {
        int number = nextOrderNumber.incrementAndGet();
        return "ORD-" + number;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        OrderNumberGenerator orderNumberGenerator = new OrderNumberGenerator();

        Set<String> orderNumbers = ConcurrentHashMap.newKeySet();
        int amountOfTasks = 500;
        for (int i = 0; i < amountOfTasks; i++) {
            executorService.submit(() -> {
                String orderNum = orderNumberGenerator.generateOrderNumber();
                orderNumbers.add(orderNum);
                System.out.println("[" + Thread.currentThread().getName() + "] Generated: " + orderNum);
            });
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

        int totalOrdersGenerated = (orderNumberGenerator.nextOrderNumber.get() - 1000);
        int uniqueOrderNumbers = orderNumbers.size();
        System.out.println("Total orders generated: " + totalOrdersGenerated);
        System.out.println("Unique order numbers: " + uniqueOrderNumbers);
        System.out.println("All unique: " + (totalOrdersGenerated == uniqueOrderNumbers));

        // PYTANIE (odpowiedz w komentarzu):
        // Co by sie stalo gdybys uzyla zwyklego int
        // zamiast AtomicInteger do generowania numerow?
        //gdybym uzyla zwyklego int zamiast Atomic
        // to nie mialabym mozliwosciskorzystac z atomowych metod np. incrementAndGet()
        // wiec bym musiala metode zablokowac synchronized a jezeli nie to bym stracila thread-safe
        // zrobilam blad w metodzie generateOrderNumber() i wlasnie mialam do czynienia z roznymi wartosciami
        // bo ktorys watek nie byl zabezpieczony i inkrementowal zla wartosc
        // Czy numery zawsze bylyby unikalne?
        // nie wtedy numery by sie zmienialy
    }
}
