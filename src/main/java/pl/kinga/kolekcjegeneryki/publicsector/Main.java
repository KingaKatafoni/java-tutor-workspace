package pl.kinga.kolekcjegeneryki.publicsector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CaseQueue queue = new CaseQueue();
        queue.addCase(new CaseTicket("PL/098776/001", "Jan Kowalski", "Payment delay"));
        queue.addCase(new CaseTicket("DE/098776/002", "Kamila Wozniakowska", "Personal id exchange"));
        queue.addCase(new CaseTicket("EU/098776/003", "Emilian Prus", "Finances charges"));
        System.out.println(queue);
        queue.addUrgentCase(new CaseTicket("PL/098776/004", "Stanislaw Poniatowski", "Birth certificate"));
        queue.addUrgentCase(new CaseTicket("EU/098776/005", "Emilia Paciorek", "Operational costs"));
        System.out.println(queue);

        System.out.println("Case proceeded: " + queue.processNextCase());
        System.out.println("Case proceeded: " + queue.processNextCase());
        System.out.println(queue);
        System.out.println("Next case" + queue.peekNextCase());
        System.out.println(queue);

        // #Benchmark: ArrayList vs LinkedList
        final int CONST_10_000 = 10_000;
        final int CONST_50_000 = 50_000;
        final int CONST_100_000 = 100_000;

        //Test1
        List<Integer> listArrayed1 = new ArrayList<>();
        List<Integer> listArrayed2 = new ArrayList<>();
        List<Integer> listArrayed3 = new ArrayList<>();
        List<Integer> filledArrayList1 = ListComparisonBenchmark.createFilledList(listArrayed1, CONST_10_000);
        List<Integer> filledArrayList2 = ListComparisonBenchmark.createFilledList(listArrayed2, CONST_50_000);
        List<Integer> filledArrayList3 = ListComparisonBenchmark.createFilledList(listArrayed3, CONST_100_000);


        List<Integer> listLinked1 = new LinkedList<>();
        List<Integer> listLinked2 = new LinkedList<>();
        List<Integer> listLinked3 = new LinkedList<>();
        List<Integer> filledLinkedList1 = ListComparisonBenchmark.createFilledList(listLinked1, CONST_10_000);
        List<Integer> filledLinkedList2 = ListComparisonBenchmark.createFilledList(listLinked2, CONST_50_000);
        List<Integer> filledLinkedList3 = ListComparisonBenchmark.createFilledList(listLinked3, CONST_100_000);
        System.out.println("--- add(0, element) ---");
        System.out.println("ArrayList " + CONST_10_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledArrayList1, CONST_10_000) + "ns"); // O(n) -> elements are moved
        System.out.println("LinkedList " + CONST_10_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledLinkedList1, CONST_10_000) + "ns"); // O(1) -> element is just added to the head, new node is created none elements are moving
        System.out.println();
        System.out.println("ArrayList " + CONST_50_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledArrayList2, CONST_50_000) + "ns");
        System.out.println("LinkedList " + CONST_50_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledLinkedList2, CONST_50_000) + "ns");
        System.out.println();
        System.out.println("ArrayList " + CONST_100_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledArrayList3, CONST_100_000) + "ns");
        System.out.println("LinkedList " + CONST_100_000 + ": " + ListComparisonBenchmark.measureAddToBeginning(filledLinkedList3, CONST_100_000) + "ns");
        System.out.println();
        //Test2
        List<Integer> arrayListGet = new ArrayList<>();
        List<Integer> linkedListGet = new LinkedList<>();

        List<Integer> filledArrayListGet = ListComparisonBenchmark.createFilledList(arrayListGet, CONST_100_000);
        List<Integer> filledLinkedListGet = ListComparisonBenchmark.createFilledList(linkedListGet, CONST_100_000);
        System.out.println("--- get(index) — 10_000 lookups ---");
        System.out.println("ArrayList (size " + CONST_100_000 + "): " + ListComparisonBenchmark.measureGetByIndex(arrayListGet, CONST_10_000) + "ns"); // O(1) we have exact index and exact access to the element
        System.out.println("LinkedList (size " + CONST_100_000 + "): " + ListComparisonBenchmark.measureGetByIndex(linkedListGet, CONST_10_000) + "ns"); // O(n) we have to check all elements
    }
}
