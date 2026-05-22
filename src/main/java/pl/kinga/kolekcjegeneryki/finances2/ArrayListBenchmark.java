package pl.kinga.kolekcjegeneryki.finances2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBenchmark {
    static long measureAddToEnd(int count) {
        List<Integer> listBench = new ArrayList<Integer>();
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            listBench.add(i);
        }
        long end = System.nanoTime();

        return end - start;
    }

    static long measureAddToBeginning(int count) {
        List<Integer> listBench = new ArrayList<Integer>();
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            listBench.add(0, i);
        }
        long end = System.nanoTime();

        return end - start;
    }

    static long measureGetByIndex(int size) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            integerList.add(i);
        }
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            integerList.get(i % size);
        }

        long end = System.nanoTime();
        return end - start;
    }

    static long measureContains(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            list.contains(size - 1);
        }
        long end = System.nanoTime();

        return end - start;
    }
}
