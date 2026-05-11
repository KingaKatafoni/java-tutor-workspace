package pl.kinga.oop.publicsector;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoterRegistry votersKrakow = new VoterRegistry("Krakow - obwod 7");

        votersKrakow.addVoter("Karol Michnik");
        votersKrakow.addVoter("Emilia Pasut");
        votersKrakow.addVoter("Beniamin Szczoch");
        votersKrakow.addVoter("Zygmunt Stary");

        List<String> voters = votersKrakow.getVoters();
        System.out.println("Downloaded list: " + voters);
        voters.clear();
        System.out.println("New list after list.clear: " + voters);
        System.out.println("Original list after list.clear(): " + votersKrakow);


        System.out.println("getCount(): " + votersKrakow.getVoterCount());
        System.out.println("hasVoter(): " + votersKrakow.hasVoter("Beniamin Szczoch"));

        System.out.println("toString(): " + votersKrakow.toString());

    }
}
