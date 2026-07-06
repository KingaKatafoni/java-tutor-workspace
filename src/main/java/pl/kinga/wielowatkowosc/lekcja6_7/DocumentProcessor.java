package pl.kinga.wielowatkowosc.lekcja6_7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DocumentProcessor {
    private final Queue<String> documentQueue = new LinkedList<>();
    private final int[] proccesedCount;

    public DocumentProcessor(int clerkCount) {
        proccesedCount = new int[clerkCount];
    }

    int amountOfDocuments = 60;

    public void addDocuments() {
        for (int i = 0; i < amountOfDocuments; i++) {
            documentQueue.add("DOK-0" + (i + 1));
        }
    }

    public void processDocuments(int clerkId) throws InterruptedException {
        while (true) {
            String doc;
            synchronized (documentQueue) {
                if (documentQueue.isEmpty()) {
                    return;
                }
                doc = documentQueue.poll();
            }
            Thread.sleep(50);
            proccesedCount[clerkId]++;
            System.out.println("Clerk-" + clerkId + " Processed " + doc);
        }
    }

    public static void main(String[] args) {
        DocumentProcessor documentProcessor = new DocumentProcessor(4);
        documentProcessor.addDocuments();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            int clerkId = i;
            executorService.submit(() -> {

                try {
                    documentProcessor.processDocuments(clerkId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Clerk-0: proccessed " + documentProcessor.proccesedCount[0]);
        System.out.println("Clerk-1: proccessed " + documentProcessor.proccesedCount[1]);
        System.out.println("Clerk-2: proccessed " + documentProcessor.proccesedCount[2]);
        System.out.println("Clerk-3: proccessed " + documentProcessor.proccesedCount[3]);
        System.out.println("Total processed: " + Arrays.stream(documentProcessor.proccesedCount).sum());

        // rozklad dokumentow jest rownomierny
        // Clerk-0: proccessed 15
        //Clerk-1: proccessed 15
        //Clerk-2: proccessed 15
        //Clerk-3: proccessed 15
        //Total processed: 60

        // jezeli Thread.sleep(50) byloby wewnatzr synchronized to zadania wykonywalyby sie sekwencyjnie byly by przyblokowane
        // widac ze kod wykonuje sie dluzej
        // rozklad jest dokumentow jest rowniez rownomierny
        // a jak sleep jest poza synchronized moga dzialac rownolegle

    }
}
