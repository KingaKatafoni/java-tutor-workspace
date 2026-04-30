package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NarzedziaOperatora {
    public static final String PREFIKS_KRAJOWY = "+48";
    public static final BigDecimal STAWKA_VAT = new BigDecimal("0.23");
    public static final int DLUGOSC_NUMERU = 9;
    public static final String WALUTA = "PLN";

    private NarzedziaOperatora() {

    }

    public static boolean czyNumerPoprawny(String numer) {
        if (numer == null) return false;
        char pierwszyElement = numer.charAt(0);
        return (numer.length() == DLUGOSC_NUMERU) && (pierwszyElement == '5' || pierwszyElement == '6' || pierwszyElement == '7' || pierwszyElement == '8');
    }

    public static String formatujNumer(String numer) {
        if (!czyNumerPoprawny(numer)) {
            return "Niepoprawny numer";
        } else {
            return PREFIKS_KRAJOWY + " " + numer.substring(0, 3) + " " + numer.substring(3, 6) + " " + numer.substring(6, 9);
        }
    }

    public static BigDecimal obliczBrutto(BigDecimal cenaNetto) {
        if (cenaNetto == null || cenaNetto.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        } else {
            return (cenaNetto.add((cenaNetto.multiply(STAWKA_VAT)))).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static BigDecimal obliczRabat(BigDecimal cena, int procentRabatu) {
        if (procentRabatu < 0 || procentRabatu > 100) {
            return cena;
        } else {
            return ((cena.multiply(new BigDecimal(100 - procentRabatu).divide(new BigDecimal(100),2, RoundingMode.HALF_UP)))).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static String opisPakietu(String nazwa, BigDecimal cenaNetto, int limitGB, int limitMinut) {
        StringBuilder opis = new StringBuilder();
        opis.append("\nPakiet: ")
                .append(nazwa)
                .append("\nCena: ")
                .append(cenaNetto)
                .append(" ")
                .append(WALUTA)
                .append(" netto ")
                .append("(")
                .append(obliczBrutto(cenaNetto))
                .append(" ")
                .append(WALUTA)
                .append(" brutto)")
                .append("\nDane: ")
                .append(limitGB)
                .append(" GB")
                .append(" | Minuty: ")
                .append(limitMinut)
                .append(" min");

        return opis.toString();

    }
}
