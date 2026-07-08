package pl.kinga.wielowatkowosc.lekcja6_8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VotingSystem {
    private ConcurrentHashMap<String, Integer> votes = new ConcurrentHashMap<>();
    //private HashMap<String, Integer> votes = new HashMap<>();

    public void vote(String candidate) {
        votes.merge(candidate, 1, Integer::sum);
    }

    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int amountOfVotes = 1000;
        String[] candidates = {"Kowalski", "Nowak", "Wisniewska"};

        for (int c = 0; c < candidates.length; c++) {
            String candidate = candidates[c];
            for (int i = 0; i < amountOfVotes; i++) {
                executorService.submit(() -> votingSystem.vote(candidate));
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        votingSystem.votes.forEach((k, v) -> System.out.println(k + ": " + v + " votes."));
        int totalValue = votingSystem.votes.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Total votes: " + totalValue);

        // z zastosowaniem ConcurrentHashMap
        // Wisniewska: 1000 votes.
        //Kowalski: 1000 votes.
        //Nowak: 1000 votes.
        //Total votes: 3000

        // bez ConcurrentHashMap
        // po kilku wywyłaniach za kazdym jest rozna ilosc glosow ale nie osiaga 3000
        //Wisniewska: 893 votes.
        //Kowalski: 854 votes.
        //Nowak: 865 votes.
        //Total votes: 2612

        // merge() jest bezpieczne bez synchronized bo to atomowa metoda czyli zabezpiecza przed race condition na wartosciach
        //get() + put() to dwie opearcje mozemy zgubic wynik przez race condition

    }
}
