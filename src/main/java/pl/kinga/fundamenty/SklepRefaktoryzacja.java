package pl.kinga.fundamenty;

import static pl.kinga.SklepRefaktoryzacja.SklepInternetowy.wyswietlDaneProduktu;

public class SklepRefaktoryzacja {
    public class SklepInternetowy {

        static final double VAT = 0.23;
        static final int MAX_ILOSC_W_KOSZYKU = 100;

        public static double obliczCeneKoncowa(double cenaZaSztuke, int iloscSztuk) {
            double cena = cenaZaSztuke * iloscSztuk;
            cena += (cena * VAT);
            if (iloscSztuk >= 10) {
                cena *= 0.9; // rabat 10%
            }
            return cena;
        }

        public static boolean czyMoznaDodacDoKoszyka(int iloscSztuk) {
            return iloscSztuk <= MAX_ILOSC_W_KOSZYKU;
        }

        public static void wyswietlDaneProduktu(String nazwaProduktu, double cenaZaSztuke, int ilosc) {
            double cenaKoncowa = obliczCeneKoncowa(cenaZaSztuke, ilosc);
            boolean moznaDodacDoKoszyka = czyMoznaDodacDoKoszyka(ilosc);

            System.out.println("Produkt: " + nazwaProduktu);
            System.out.println("Cena za szt: " + cenaZaSztuke);
            System.out.println("Ilosc: " + ilosc);
            System.out.println("Cena koncowa z VAT: " + cenaKoncowa);

            String komunikatCzyMoznaDodacDoKoszyka = moznaDodacDoKoszyka ? "Mozna dodac do koszyka" : "Za duzo sztuk! Max: " + MAX_ILOSC_W_KOSZYKU;

            System.out.println(komunikatCzyMoznaDodacDoKoszyka);
            System.out.println("---");
        }

    }

    public static void main(String[] args) {
        wyswietlDaneProduktu("Koszulka polo", 79.99, 3);
        wyswietlDaneProduktu("Dlugopis", 2.50, 15);
        wyswietlDaneProduktu("Monitor 27 cali", 1299.00, 150);
    }

}
