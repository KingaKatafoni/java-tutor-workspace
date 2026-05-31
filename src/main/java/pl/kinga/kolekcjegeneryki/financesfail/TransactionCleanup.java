package pl.kinga.kolekcjegeneryki.financesfail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionCleanup {

    public static void removeUnsafe(List<String> list) {
        for (String transaction : list) {
            if (transaction.contains("CANCELLED")) {
                list.remove(transaction);
            }
        }
    }

    public static void removeWithIterator(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().contains("CANCELLED")) {
                it.remove();
            }
        }
    }

    public static void removeWithRemoveIf(List<String> list) {
        list.removeIf(status -> status.contains("CANCELLED"));
    }

    public static List<String> removeToNewList(List<String> list) {
        List<String> uncancelledList = new ArrayList<>();
        for (String element : list) {
            if (!element.contains("CANCELLED")) {
                uncancelledList.add(element);
            }
        }
        return uncancelledList;
    }


    public static void main(String[] args) {
        List<String> transactions = new ArrayList<>(List.of(
                "TX-001:COMPLETED", "TX-002:CANCELLED", "TX-003:COMPLETED",
                "TX-004:CANCELLED", "TX-005:PENDING", "TX-006:CANCELLED"));

        //TransactionCleanup.removeUnsafe(new ArrayList<>(transactions));// ConcurrentModificationException
        List<String> copy1 = new ArrayList<>(transactions);
        TransactionCleanup.removeWithIterator(copy1);
        System.out.println("Remove with Iterator: " + copy1);
        List<String> copy2 = new ArrayList<>(transactions);
        TransactionCleanup.removeWithRemoveIf(copy2);
        System.out.println("Remove with removeIf(): " + copy2);
        System.out.println("Remove to new list: " + TransactionCleanup.removeToNewList(new ArrayList<>(transactions)));
    }
}

