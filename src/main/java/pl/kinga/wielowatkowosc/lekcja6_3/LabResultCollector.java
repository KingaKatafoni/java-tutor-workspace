package pl.kinga.wielowatkowosc.lekcja6_3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class LabResultCollector {
    record LabResult(String labName, String testName, String result, long responseTimeMs) {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Laboratoria:
        // "CentralLab"  -> testName: "Morphology",  result: "WBC: 7.2, RBC: 4.8, HGB: 14.1", responseTime: 1800ms
        // "BioLab"      -> testName: "Lipid panel", result: "Cholesterol: 195, HDL: 55, LDL: 120", responseTime: 2500ms
        // "GenomeLab"   -> testName: "Glucose",     result: "Fasting glucose: 92 mg/dL", responseTime: 800ms
        // "PathologyLab"-> testName: "TSH",         result: "TSH: 2.1 mIU/L", responseTime: 1200ms

        long startTime = System.currentTimeMillis();
        // 1. Stworz ExecutorService z pula 4 watkow.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 2. Dla kazdego laboratorium stworz Callable<LabResult>.
        Callable<LabResult> labCentralLab = () -> {
            Thread.sleep(1800);
            return new LabResult("CentralLab", "Morphology", "WBC: 7.2, RBC: 4.8, HGB: 14.1", 1800);
        };
        Callable<LabResult> labBioLab = () -> {
            Thread.sleep(2500);
            return new LabResult("BioLab", "Lipid panel", "Cholesterol: 195, HDL: 55, LDL: 120", 2500);
        };
        Callable<LabResult> labGenomeLab = () -> {
            Thread.sleep(800);
            return new LabResult("GenomeLab", "Glucose", "Fasting glucose: 92 mg/dL", 800);
        };
        Callable<LabResult> labPathologyLab = () -> {
            Thread.sleep(1200);
            return new LabResult("PathologyLab", "TSH", "TSH: 2.1 mIU/L", 1200);
        };

        // 3. Submit all, get all.
        List<Future<LabResult>> futures = new ArrayList<>();

        Future<LabResult> futureCentralLab = executor.submit(labCentralLab);
        Future<LabResult> futureBioLab = executor.submit(labBioLab);
        Future<LabResult> futureGenomeLab = executor.submit(labGenomeLab);
        Future<LabResult> futurePathologyLab = executor.submit(labPathologyLab);
        futures.add(futureCentralLab);
        futures.add(futureBioLab);
        futures.add(futureGenomeLab);
        futures.add(futurePathologyLab);

        List<LabResult> results = new ArrayList<>();
        for (Future<LabResult> result : futures) {
            LabResult r = result.get();
            results.add(r);
            // 4. Wypisz wyniki w formacie:
            System.out.println("[" + r.labName + "] " + r.testName + ": " + r.result + " (" + r.responseTimeMs + " ms)");
        }
        // 5. Znajdz laboratorium ktore odpowiedzialo NAJSZYBCIEJ
        results.stream()
                .min(Comparator.comparing(LabResult::responseTimeMs))
                .ifPresent(r -> System.out.println("Fastest lab: " + r.labName));

        // 6. Znajdz laboratorium ktore odpowiedzialo NAJWOLNIEJ. Wypisz.

        results.stream()
                .max(Comparator.comparing(LabResult::responseTimeMs))
                .ifPresent(r -> System.out.println("Slowest Lab: " + r.labName));

        // 7. Zmierz calkowity czas — powinno byc ~2500ms.

        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
        Long sum = results.stream()
                .map(LabResult::responseTimeMs)
                .reduce(
                        0L,
                        Long::sum);
        System.out.println("Total summed time: " + sum + " ms");

        // 8. shutdown()
        executor.shutdown();
    }
}
