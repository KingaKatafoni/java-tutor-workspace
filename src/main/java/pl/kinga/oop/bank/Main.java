package pl.kinga.oop.bank;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        BankClient clientOne = new BankClient("93041598009", "Krzysztof Krawczyk", "opka.popka@gmail.com");
        BankClient clientTwo = new BankClient("93041598009", "Krzysztof Krawczyk", "lupka.dupka@gmail.com");
        System.out.print("clientOne == clientTwo: ");
        System.out.println(clientOne == clientTwo);
        System.out.print("clientOne.equals(clientTwo): ");
        System.out.println(clientOne.equals(clientTwo));
        System.out.print("clientOne.hashCode() == clientTwo.hashCode(): ");
        System.out.println(clientOne.hashCode() == clientTwo.hashCode());

        HashSet<BankClient> bankClients = new HashSet<>();
        bankClients.add(clientOne);

        if (bankClients.contains(clientTwo)) {
            System.out.println("To ta sama osoba");
        } else {
            System.out.println("To nie ta sama osoba");
        }
    }
}
