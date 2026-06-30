package pl.kinga.wielowatkowosc.lekcja6_2.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ShipmentTracker {
    record Parcel(String trackingId, String warehouse, int scanTimeMs) {
    }

    public static void main(String[] args) throws InterruptedException {
        List<Parcel> parcels = List.of(
                new Parcel("PKG001", "Warsaw", 800),
                new Parcel("PKG002", "Warsaw", 600),
                new Parcel("PKG003", "Krakow", 1200),
                new Parcel("PKG004", "Krakow", 900),
                new Parcel("PKG005", "Poznan", 500),
                new Parcel("PKG006", "Poznan", 1100),
                new Parcel("PKG007", "Gdansk", 700)
        );


        System.out.println("--- 1# Thread for each parcel ---");
        long startTime = System.currentTimeMillis();
        AtomicLong sumOfTimeMS = new AtomicLong();
        List<Thread> threads = new ArrayList<>();
        parcels.forEach(t -> {
            Thread th = new Thread(() -> {
                System.out.println("Scanning " + t.trackingId + " at " + t.warehouse + "... [" + Thread.currentThread().getName() + "]");
                try {
                    Thread.sleep(t.scanTimeMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Scanned " + t.trackingId + " at " + t.warehouse + " [" + Thread.currentThread().getName() + "]");
            });
            th.start();
            threads.add(th);

            sumOfTimeMS.addAndGet(t.scanTimeMs);

        });

        threads.forEach(
                th -> {
                    try {
                        th.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
        System.out.println("Sum of all scanTimeMs " + sumOfTimeMS + " ms");

        // 5. -> After change from start() to run() total time changed from 1209ms to 5829ms
    }

}
