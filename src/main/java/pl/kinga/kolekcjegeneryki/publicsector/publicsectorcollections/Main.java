package pl.kinga.kolekcjegeneryki.publicsector.publicsectorcollections;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoterRegistry voterRegistry = new VoterRegistry();

        voterRegistry.addVoter("Kowalski");
        voterRegistry.addVoter("Polka");
        voterRegistry.addVoter("Kowalski");
        voterRegistry.addVoter("Kowalski");
        voterRegistry.addVoter("Kopek");
        voterRegistry.addVoter("Gilowska");
        voterRegistry.addVoter("Hybka");
        voterRegistry.addVoter("Kowalski");

        System.out.println("------All voters--------");
        voterRegistry.printAll();
        System.out.println("-------after sort--------");
        voterRegistry.sortAlphabetically();
        voterRegistry.printAll();
        System.out.println("-------reverse--------");
        voterRegistry.reverseOrder();
        voterRegistry.printAll();
        System.out.println("-------shuffle--------");
        voterRegistry.shuffleForAudit();
        voterRegistry.printAll();
        System.out.println();
        System.out.println("------------------");
        System.out.println("Min : " + voterRegistry.getFirst());
        System.out.println("Max: " + voterRegistry.getLast());
        System.out.println("Frequency: " + voterRegistry.countOccurrences("Kowalski"));
        List<String> viewVoters = voterRegistry.getVotersReadOnly();
        //viewVoters.add("Pol"); // UnsupportedOperationException


    }
}
