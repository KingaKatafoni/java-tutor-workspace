package pl.kinga.kolekcjegeneryki.financespecs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args){
        List<InternalTransaction> internalTransactions = new ArrayList<>(
                List.of(
                        new InternalTransaction("INT/987/001", 4.0, "Business"),
                        new InternalTransaction("INT/982/002", 21.0, "R&D"),
                        new InternalTransaction("INT/921/003", 3.0, "Analyze")));

        List<Transaction> transactions = new ArrayList<>();

        TransferService.transferAll(internalTransactions, transactions);
        System.out.println(transactions);

        System.out.println("--------Object--------");
        List<Object> objects = new ArrayList<>();
        TransferService.transferAll(internalTransactions, objects);
        System.out.println(objects);
    }
}
