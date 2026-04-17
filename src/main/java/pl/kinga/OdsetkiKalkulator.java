package pl.kinga;

public class OdsetkiKalkulator {
    public static void main(String[] args) {
        // Klient wplaca na roczna lokate:
        double kwotaWplaty = 10000.0;
        double oprocentowanieRoczne = 0.05;  // 5%
        int liczbaLat = 3;

        // ZADANIE: oblicz i wypisz:
        // 1. Odsetki po 1 roku (kwota * oprocentowanie)
        double odsetkiRoczne = kwotaWplaty * oprocentowanieRoczne;
        // 2. Laczna kwota po 1 roku (wplata + odsetki)
        double kwotaPoRoku = kwotaWplaty + odsetkiRoczne;
        // 3. Kwota po 'liczbaLat' z procentem prostym
        //    (wplata + wplata * oprocentowanie * liczbaLat)
        double kwotaPoXLat = kwotaWplaty + kwotaWplaty * oprocentowanieRoczne * liczbaLat;
        // 4. Uzyj operatora += zeby dodac odsetki roczne
        //    do zmiennej 'saldo' — 3 razy (symulacja 3 lat)

        System.out.println("Odsetki roczne: " + odsetkiRoczne);
        System.out.println("Kwota po roku: " + kwotaPoRoku);
        System.out.println("Kwota po " + liczbaLat + " latach: " + kwotaPoXLat);

        double saldo = kwotaWplaty;
         saldo += odsetkiRoczne;
        System.out.println("Saldo po roku 1: " + saldo);
         saldo += odsetkiRoczne;
        System.out.println("Saldo po roku 2: " + saldo);
         saldo += odsetkiRoczne;
        System.out.println("Saldo po roku 3: " + saldo);


    }
}
