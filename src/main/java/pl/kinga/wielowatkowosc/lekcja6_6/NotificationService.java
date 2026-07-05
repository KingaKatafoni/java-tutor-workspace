package pl.kinga.wielowatkowosc.lekcja6_6;

import java.time.LocalTime;

public class NotificationService {
    private volatile boolean running = true;

    public void sendNotifications() throws InterruptedException {
        while (running) {
            System.out.println("[ " + LocalTime.now() + "] Sending notification...");
            Thread.sleep(500);
        }
        System.out.println("Notification service stopped.");
    }

    public static void main(String[] args) throws InterruptedException {
        NotificationService notificationService = new NotificationService();
        Thread thread1 = new Thread(() -> {
            try {
                notificationService.sendNotifications();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread1.start();
        Thread.sleep(3000);
        notificationService.running = false;
        System.out.println("Stop signal sent");
        thread1.join();
        System.out.println("Main finished");

    }
    // 4. Uruchom. Czy watek sie zatrzymal?
    //    Moze sie zatrzymac, moze NIE — to zalezy od JVM i CPU cache.
    //    Zapisz obserwacje w komentarzu.
    // watek sie zatrzymuje dokladnie po 3 sek nastepnie
    //[ 21:28:14.786859] Sending notification...
    //[ 21:28:15.293270] Sending notification...
    //[ 21:28:15.798739] Sending notification...
    //[ 21:28:16.304203] Sending notification...
    //[ 21:28:16.807733] Sending notification...
    //[ 21:28:17.308546] Sending notification...
    //Stop signal sent
    //Notification service stopped.
    //Main finished


    // 5. Dodaj volatile do pola running.
    //    Uruchom ponownie. Czy teraz zawsze sie zatrzymuje?

    // PYTANIE (odpowiedz w komentarzu):
    // Dlaczego bez volatile watek MOZE nie zauwazyc zmiany running = false?
    // Uzyj slowa "cache" w odpowiedzi.
    // teraz zawsze sie zatrzymuje, pniewaaż volatile powoduje ze zmiany pomiedzy watkami sa widoczne
    //dzieje sie tak poniewaz zmiany ida do pamieci glownej a nie z cache CPU

}
