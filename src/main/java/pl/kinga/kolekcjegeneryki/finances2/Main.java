package pl.kinga.kolekcjegeneryki.finances2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args){
        TransactionLog transactionLog = new TransactionLog();

        transactionLog.addTransaction("Withdrawal"); // O(1) adding element at the end of the List
        transactionLog.addTransaction("Money transfer");
        transactionLog.addTransaction("Money transfer international");
        transactionLog.addTransaction("Withdrawal");
        transactionLog.addTransaction("Income");

        System.out.println(transactionLog.toString());

        transactionLog.addUrgentTransaction("Money transfer country");//O(n) first elements are moved to the right and then new element is placed at the first position
        transactionLog.addUrgentTransaction("Withdrawal Dollars");

        System.out.println(transactionLog.toString());

        System.out.println(transactionLog.getTransaction(2)); // O(1) we know the index so we are getting exact value

        transactionLog.removeOldestTransaction(); // O(n) we have to move values after removed one
        transactionLog.removeNewestTransaction(); // O(1) we don't have to move the rest elements

        System.out.println(transactionLog);

        System.out.println(transactionLog.containsTransaction("Withdrawal")); // O(n) we have to search by all List elements

        final Integer CONST_10_000 = 10_000;
        final Integer CONST_50_000 = 50_000;
        final Integer CONST_100_000 = 100_000;
        final Integer CONST_1_000_000 = 1_000_000;
        System.out.println("--- add() to end vs add(0, element) ---");
        System.out.println("Size " + CONST_10_000 + ": end = " + ArrayListBenchmark.measureAddToEnd(CONST_10_000) + "ns, " + "beginning = " + ArrayListBenchmark.measureAddToBeginning(CONST_10_000) + "ns");
        System.out.println("Size " + CONST_50_000 + ": end = " + ArrayListBenchmark.measureAddToEnd(CONST_50_000) + "ns, " + "beginning = " + ArrayListBenchmark.measureAddToBeginning(CONST_50_000) + "ns");
        System.out.println("Size " + CONST_100_000 + ": end = " + ArrayListBenchmark.measureAddToEnd(CONST_100_000) + "ns, " + "beginning = " + ArrayListBenchmark.measureAddToBeginning(CONST_100_000) + "ns");


        System.out.println("--- get(index) ---");
        System.out.println("Size " + CONST_10_000 + ": 10000 gets = " + ArrayListBenchmark.measureGetByIndex(CONST_10_000) + "ns");
        System.out.println("Size " + CONST_100_000 + ": 10000 gets = " + ArrayListBenchmark.measureGetByIndex(CONST_100_000) + "ns");
        System.out.println("Size " + CONST_1_000_000 + ": 10000 gets = " + ArrayListBenchmark.measureGetByIndex(CONST_1_000_000) + "ns");

        System.out.println("--- contains() ---");
        System.out.println("Size " + CONST_10_000 + ": 100 contains = " + ArrayListBenchmark.measureContains(CONST_10_000) + "ns");
        System.out.println("Size " + CONST_100_000 + ": 100 contains = " + ArrayListBenchmark.measureContains(CONST_100_000) + "ns");
    }
}
