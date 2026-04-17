package pl.kinga;

public class PrzewidzWynik {
    public static void main(String[] args) {
        // --- Fragment A ---
        int a = 7;
        int b = 2;
        System.out.println(a / b);           // Twoja predykcja: 3
        System.out.println((double) a / b);  // Twoja predykcja: 3.5
        System.out.println(a / (double) b);  // Twoja predykcja: 3.5
        System.out.println((double)(a / b)); // Twoja predykcja: 3.0

        // --- Fragment B ---
        double cena = 29.99;
        int cenaInt = (int) cena;
        System.out.println(cenaInt);         // Twoja predykcja: 29

        // --- Fragment C ---
        Integer x = null;
        //System.out.println(x + 1);        // Twoja predykcja: wyskoczy błąd NPE

        // --- Fragment D ---
        char c = '5';
        int cyfra = c - '0';
        System.out.println(cyfra);           // Twoja predykcja: 5
    }
}
