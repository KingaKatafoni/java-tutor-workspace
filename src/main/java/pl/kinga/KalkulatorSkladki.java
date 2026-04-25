package pl.kinga;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class KalkulatorSkladki {
    /**
     * Załozenia poczatkowe
     * - **Skladka bazowa:** 1200.00 zl
     * - **Wspolczynnik wieku:** ponizej 25 lat → x 1.8, 25-60 lat → x 1.0, powyzej 60 → x 1.5
     * - **Znizka za brak szkod:** jesli klient nie mial szkod przez 3+ lata → -15%
     * - **Skladka miesieczna:** roczna / 12 (zaokraglenie do groszy, HALF_UP)
     */

    public static final BigDecimal SKLADKA_BAZOWA = new BigDecimal("1200.00");
    public static String znizkaZaBrakSzkod = "";

    public static BigDecimal pobierzWspolczynnikWieku(int wiek) {
        BigDecimal wspolczynnik = new BigDecimal("0");

        if (wiek < 25) {
            wspolczynnik = new BigDecimal("1.8");
        } else if (wiek <= 60) {
            wspolczynnik = new BigDecimal("1.0");
        } else {
            wspolczynnik = new BigDecimal("1.5");
        }
        
        return wspolczynnik;
    }

    public static BigDecimal obliczSkladkeRoczna(int wiek, boolean brakSzkod, int latBezSzkod) {
        BigDecimal skladka = SKLADKA_BAZOWA.multiply(pobierzWspolczynnikWieku(wiek));

        if (brakSzkod && latBezSzkod > 3) {
            skladka = skladka.multiply(new BigDecimal("0.85"));
            znizkaZaBrakSzkod = " -15%";
        } else {
            znizkaZaBrakSzkod = " brak";
        }

        return skladka.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal obliczSkladkeMiesieczna(BigDecimal skladkaRoczna) {
        return skladkaRoczna.divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
    }

    public static void wyswietlOferte(String imie, int wiek, boolean brakSzkod, int latBezSzkod) {
        StringBuilder oferta = new StringBuilder();
        String opisWspolczynnika;

        if (wiek < 25) {
            opisWspolczynnika = " (wiek < 25)";
        } else if (wiek <= 60) {
            opisWspolczynnika = " (wiek 25-60)";
        } else {
            opisWspolczynnika = " (wiek > 60)";
        }

        BigDecimal wspolczynnik = pobierzWspolczynnikWieku(wiek);
        BigDecimal skladkaRoczna = obliczSkladkeRoczna(wiek, brakSzkod, latBezSzkod);
        BigDecimal skladkaMiesieczna = obliczSkladkeMiesieczna(skladkaRoczna);

        oferta.append("=== OFERTA UBEZPIECZENIA ===")
                .append("\nKlient: ")
                .append(imie)
                .append(" (")
                .append(wiek)
                .append(" lat)")
                .append("\nSkladka bazowa: ")
                .append(SKLADKA_BAZOWA)
                .append(" zl")
                .append("\nWspolczynnik:  x")
                .append(wspolczynnik)
                .append(opisWspolczynnika)
                .append("\nZnizka za brak szkod: ")
                .append(znizkaZaBrakSzkod)
                .append("\nSkladka roczna: ")
                .append(skladkaRoczna)
                .append(" zl")
                .append("\nSkladka miesieczna: ")
                .append(skladkaMiesieczna)
                .append(" zl")
                .append("\n============================\n");

        System.out.println(oferta);
    }

    public static void main(String[] args) {
        wyswietlOferte("Tomasz", 22, true, 0);
        wyswietlOferte("Ewa", 40, true, 5);
        wyswietlOferte("Krystyna", 67, false, 0);
    }
}
