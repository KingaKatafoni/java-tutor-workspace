package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PracownikUrzedu {
    private String imie;
    private String nazwisko;
    private String numerSluzby;
    private BigDecimal stawkaGodzinowa;

    public PracownikUrzedu(){}

    public PracownikUrzedu(String imie, String nazwisko, String numerSluzby, BigDecimal stawkaGodzinowa) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerSluzby = numerSluzby;
        this.stawkaGodzinowa = stawkaGodzinowa;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNumerSluzby() {
        return numerSluzby;
    }

    public BigDecimal getStawkaGodzinowa() {
        return stawkaGodzinowa.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal obliczWynagrodzenieMiesieczne(int liczbaGodzin) {
        return (stawkaGodzinowa.multiply(new BigDecimal(liczbaGodzin)).setScale(2, RoundingMode.HALF_UP));
    }

    public String przedstawSie() {
        return imie +
                " " +
                nazwisko +
                ", numer sluzby: " +
                numerSluzby;
    }

    public String getTypPracownika(){
        return "Pracownik";
    }
}
