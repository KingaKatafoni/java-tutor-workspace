package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Polisa {
    private String numerPolisy;
    private String imieUbezpieczonego;
    private String nazwiskoUbezpieczonego;
    private String rodzajPolisy;
    private BigDecimal kwotaUbezpieczenia;
    private BigDecimal skladkaMiesieczna;
    private boolean aktywna;

    public Polisa(String numerPolisy, String imieUbezpieczonego, String nazwiskoUbezpieczonego, String rodzajPolisy, BigDecimal kwotaUbezpieczenia, BigDecimal skladkaMiesieczna) {
        this.numerPolisy = numerPolisy;
        this.imieUbezpieczonego = imieUbezpieczonego;
        this.nazwiskoUbezpieczonego = nazwiskoUbezpieczonego;
        this.rodzajPolisy = rodzajPolisy;
        this.kwotaUbezpieczenia = kwotaUbezpieczenia.setScale(2, RoundingMode.HALF_UP);
        this.skladkaMiesieczna = skladkaMiesieczna.setScale(2, RoundingMode.HALF_UP);
        this.aktywna = true;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public String getImieUbezpieczonego() {
        return imieUbezpieczonego;
    }

    public String getNazwiskoUbezpieczonego() {
        return nazwiskoUbezpieczonego;
    }

    public String getRodzajPolisy() {
        return rodzajPolisy;
    }

    public BigDecimal getKwotaUbezpieczenia() {
        return kwotaUbezpieczenia;
    }

    public BigDecimal getSkladkaMiesieczna() {
        return skladkaMiesieczna;
    }

    public boolean isAktywna() {
        return aktywna;
    }

    public void setRodzajPolisy(String rodzajPolisy) {
        if (rodzajPolisy == null || rodzajPolisy.isEmpty()) {
            System.out.println("Rodzaj polisy nie moze byc pusty!");
            return;
        }
        this.rodzajPolisy = rodzajPolisy;
    }

    public void setKwotaUbezpieczenia(BigDecimal kwotaUbezpieczenia) {
        if (kwotaUbezpieczenia == null || kwotaUbezpieczenia.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Kwota ubezpieczenia musi być dodatnia!\n");
            return;
        }
        this.kwotaUbezpieczenia = kwotaUbezpieczenia.setScale(2, RoundingMode.HALF_UP);
    }

    public void setSkladkaMiesieczna(BigDecimal skladkaMiesieczna) {
        if (skladkaMiesieczna == null || skladkaMiesieczna.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Skladka miesieczna musi byc dodatnia! ");
            return;
        }
        this.skladkaMiesieczna = skladkaMiesieczna.setScale(2, RoundingMode.HALF_UP);
    }

    public void dezaktywujPolise() {
        this.aktywna = false;
        System.out.println("Polisa [" + numerPolisy + "] zostala dezaktywowana.");
    }

    public void aktywujPolise() {
        this.aktywna = true;
        System.out.println("Polisa [" + numerPolisy + "] zostala aktywowana");
    }

    public void wyswietlPolise() {
        StringBuilder polisa = new StringBuilder();
        polisa.append("=== Polisa ubezpieczeniowa ===")
                .append("\nNumer: ")
                .append(numerPolisy)
                .append("\nUbezpieczony: ")
                .append(imieUbezpieczonego)
                .append(" ")
                .append(nazwiskoUbezpieczonego)
                .append("\nRodzaj: ")
                .append(rodzajPolisy)
                .append("\nKwota: ")
                .append(kwotaUbezpieczenia)
                .append(" zl")
                .append("\nSkladka: ")
                .append(skladkaMiesieczna)
                .append(" zl")
                .append("\nStatus: ");

        if (aktywna) {
            polisa.append("AKTYWNA\n");
        } else {
            polisa.append("NIEAKTYWNA\n");
        }

        System.out.println(polisa);
    }

}
