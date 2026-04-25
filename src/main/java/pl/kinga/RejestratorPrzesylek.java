package pl.kinga;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RejestratorPrzesylek {

    public static void pobierzDane() {
        Scanner scanner = new Scanner(System.in);
        String numerPrzesylki = "";
        double waga = 0.0;
        String adresDostawy = "";
        String czyPriorytetowa = "";
        String odpowiedz = "tak";
        int licznik = 0;

        while (odpowiedz.equals("tak")) {
            System.out.print("Numer przesylki: ");
            numerPrzesylki = scanner.nextLine();
            System.out.print("Waga: ");
            waga = Double.parseDouble(scanner.nextLine());
            System.out.print("Adres dostawy: ");
            adresDostawy = scanner.nextLine();
            System.out.print("Czy priorytetowa? (tak/ nie): ");
            czyPriorytetowa = scanner.nextLine();

            licznik++;
            wyswietlPodsumowanie(numerPrzesylki, waga, adresDostawy, czyPriorytetowa);
            System.out.print("Czy zarejestrowac kolejna przesylke? (tak/nie): ");
            odpowiedz = scanner.nextLine();
        }

        System.out.println("Zarejestrowano " + licznik + " przesylki. Do widzenia!");
        scanner.close();
    }

    public static BigDecimal obliczKosztDostawy(double waga, String priorytet) {
        final BigDecimal KOSZT_BAZOWY = new BigDecimal("8.50");
        BigDecimal kosztZaKilogram = new BigDecimal("2.0");
        BigDecimal wagaPaczki = BigDecimal.valueOf(waga);
        BigDecimal wspolczynnikPriorytet = new BigDecimal("1.5");
        BigDecimal kosztZaWage = kosztZaKilogram.multiply(wagaPaczki);
        BigDecimal kosztPodstawowy = KOSZT_BAZOWY.add(kosztZaWage);
        BigDecimal kosztKoncowy;

        if (priorytet.equals("tak")) {
            kosztKoncowy = kosztPodstawowy.multiply(wspolczynnikPriorytet);
        } else {
            kosztKoncowy = kosztPodstawowy;
        }
        return kosztKoncowy.setScale(2, RoundingMode.HALF_UP);
    }

    public static void wyswietlPodsumowanie(String numerPaczki, double waga, String adres, String priorytetowa) {

        StringBuilder rejestracjaPrzesylki = new StringBuilder();
        rejestracjaPrzesylki.append("\n=== REJESTRACJA PRZESYLKI ===")
                .append("\nNumer przesylki: ")
                .append(numerPaczki)
                .append("\nWaga (kg): ")
                .append(waga)
                .append("\nAdres dostawy: ")
                .append(adres)
                .append("\nPriorytetowa? (tak/nie): ")
                .append(priorytetowa);

        StringBuilder podsumowanie = new StringBuilder();
        podsumowanie
                .append("\n--- PODSUMOWANIE ---")
                .append("\nNumer: ")
                .append(numerPaczki)
                .append("\nAdres: ")
                .append(adres)
                .append("\nWaga: ")
                .append(waga)
                .append(" kg")
                .append("\nPriorytet: ")
                .append(priorytetowa.toUpperCase())
                .append("\nKoszt: ")
                .append(obliczKosztDostawy(waga, priorytetowa))
                .append(" zl")
                .append("\n--------------------\n")
                ;

        System.out.println(rejestracjaPrzesylki);
        System.out.println(podsumowanie);
    }

    public static void main(String[] args) {
        pobierzDane();
    }
}
