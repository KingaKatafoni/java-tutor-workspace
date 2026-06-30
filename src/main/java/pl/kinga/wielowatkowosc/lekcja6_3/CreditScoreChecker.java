package pl.kinga.wielowatkowosc.lekcja6_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CreditScoreChecker {
    record ScoreResult(String bureau, int score, long responseTimeMs) {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Biura i ich symulowane czasy odpowiedzi + wyniki:
        // BIK:  1500ms, score 720
        // KRD:  2000ms, score 680
        // ERIF: 1000ms, score 750

        long timeStart = System.currentTimeMillis();
        // 1. Stworz ExecutorService z pula 3 watkow.

        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 2. Dla kazdego biura stworz Callable<ScoreResult> ktory:
        Callable<ScoreResult> scoreFrom1 = () -> {
            Thread.sleep(1500);
            return new ScoreResult("BIK", 720, 1500);
        };

        Callable<ScoreResult> scoreFrom2 = () -> {
            Thread.sleep(2000);
            return new ScoreResult("KRD", 680, 2000);
        };

        Callable<ScoreResult> scoreFrom3 = () -> {
            Thread.sleep(1000);
            return new ScoreResult("ERIF", 750, 1000);
        };

        // 3. Submit all — wyslij 3 zadania, zbierz Future do listy.
        List<Future<ScoreResult>> futures = new ArrayList<>();
        Future<ScoreResult> future1 = executor.submit(scoreFrom1);
        Future<ScoreResult> future2 = executor.submit(scoreFrom2);
        Future<ScoreResult> future3 = executor.submit(scoreFrom3);
        futures.add(future1);
        futures.add(future2);
        futures.add(future3);
        int sumOfScores = 0;

        // 4. Get all — odbierz wyniki, wypisz kazdy:

        for (Future<ScoreResult> f : futures) {
            ScoreResult r = f.get();
            System.out.println(r.bureau + ": score " + r.score + " (" + r.responseTimeMs + ")");
            sumOfScores += r.score;
        }


        // 5. Oblicz sredni scoring ze wszystkich biur i wypisz.
        double averageScoring = (double) sumOfScores / 3;
        System.out.printf("Average scoring for all offices: %.2f", averageScoring);

        // 6. Zmierz calkowity czas (System.currentTimeMillis).
        long timeEnd = System.currentTimeMillis();
        System.out.println("\nTotal time: " + (timeEnd - timeStart) + " ms");

        // 7. shutdown()
        executor.shutdown();

    }
}
