package pl.kinga.kolekcjegeneryki.publicsector;

import java.util.List;

public class ListComparisonBenchmark {
    static long measureAddToBeginning(List<Integer> list, int count) {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.add(0, i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    static long measureGetByIndex(List<Integer> list, int accessCount) {
        long start = System.nanoTime();
        for (int i = 0; i < accessCount; i++) {
            list.get(i % list.size());
        }
        long end = System.nanoTime();
        return end - start;
    }

    static List<Integer> createFilledList(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
}
