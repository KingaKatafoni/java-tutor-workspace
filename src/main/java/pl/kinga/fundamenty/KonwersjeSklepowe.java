package pl.kinga.fundamenty;

public class KonwersjeSklepowe {
    public static void main(String[] args) {
        // Cena produktu (w groszach, zeby uniknac ulamkow):
        int cenaGrosze = 4999;  // 49.99 zl

        // ZADANIE 1: Przelicz cene z groszy na zlotowki (double)
        // Wypisz: "Cena: 49.99 zl"
        // Podpowiedz: uzyj konwersji — uwaga na dzielenie calkowite!
        double cenaZlotowki = (double) cenaGrosze / 100;
        System.out.println("Cena: " + cenaZlotowki + " zl");


        // ZADANIE 2: Rabat 15%. Oblicz cene po rabacie.
        // Wynik zaokraglij w dol do pelnych groszy (int).
        // Wypisz: "Cena po rabacie: XX.XX zl (XXXX gr)"

        int cenaPoRabacieGrosze = (int) (cenaGrosze * 0.85);  // 4249 gr
        double cenaPoRabacieZlotowki = (double) cenaPoRabacieGrosze / 100;  // 42.49 zl

        System.out.println("Cena po rabacie: " + cenaPoRabacieZlotowki + " zl " + "(" + cenaPoRabacieGrosze + " gr)");


        // ZADANIE 3: Klient podal ilosc sztuk jako String (np. z formularza)
        String iloscTekst = "3";
        // Sparsuj na int, oblicz wartosc zamowienia (ilosc * cena w groszach)
        // Wypisz: "Zamowienie: X szt. x XX.XX zl = XXX.XX zl"
        double wartoscZamowienia = Integer.parseInt(iloscTekst) * cenaGrosze;
        System.out.println("Zamowienie: " + iloscTekst + " szt. x " + cenaZlotowki + " zl = " + wartoscZamowienia / 100 + " zl");


        // ZADANIE 4: Kod produktu to char. Jaki jest jego kod ASCII?
        char kategoria = 'E';  // E = Elektronika
        // Wypisz: "Kategoria: E (kod: XX)"
        int kod = kategoria;
        System.out.println("Kategoria: " + kategoria + " ( kod: " + kod + ")");
    }
}
