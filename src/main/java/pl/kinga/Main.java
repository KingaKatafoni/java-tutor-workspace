package pl.kinga;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double skladkaMiesieczna = 0.1;
        double skladkaRoczna = skladkaMiesieczna * 12;
        System.out.println("Skladka roczna: " + skladkaRoczna);

        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));
        System.out.println("1.0 - 0.9 = " + (1.0 - 0.9));


        // Cwiczenia Scanner
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Podaj imie: ");
//        String imie = scanner.nextLine();
//
//        System.out.println("Czesc, " + imie + "!");
//
//        scanner.close();

        Scanner scanner = new Scanner(System.in);

        String odpowiedz = "tak";

        while (odpowiedz.equals("tak")) {
            System.out.print("Numer przesylki: ");
            String numer = scanner.nextLine();
            System.out.println("Zarejestrowano: " + numer);

            System.out.print("Dodac kolejna? (tak/nie): ");
            odpowiedz = scanner.nextLine();
        }

        System.out.println("Koniec rejestracji.");
        scanner.close();
    }


}