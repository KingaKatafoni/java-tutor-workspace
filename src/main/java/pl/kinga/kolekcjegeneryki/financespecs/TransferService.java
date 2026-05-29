package pl.kinga.kolekcjegeneryki.financespecs;

import java.util.List;

public class TransferService {
    public static void transferAll(List<? extends Transaction> source, List<? super Transaction> target){
        for (Transaction t : source){
            target.add(t);
        }
        System.out.println("Transferred transactions: " + source.size());
    }
}
