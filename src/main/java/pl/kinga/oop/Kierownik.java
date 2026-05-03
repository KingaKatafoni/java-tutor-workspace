package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kierownik extends PracownikUrzedu {
    private BigDecimal dodatekFunkcyjny;

    public Kierownik() {
    }

    public Kierownik(String imie, String nazwisko, String numerSluzby, BigDecimal stawkaGodzinowa, BigDecimal dodatekFunkcyjny) {
        super(imie, nazwisko, numerSluzby, stawkaGodzinowa);
        this.dodatekFunkcyjny = dodatekFunkcyjny;
    }

    @Override
    public BigDecimal obliczWynagrodzenieMiesieczne(int liczbaGodzin) {
        return ((super.obliczWynagrodzenieMiesieczne(liczbaGodzin)).add(dodatekFunkcyjny)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getTypPracownika(){
        return "Kierownik";
    }

}
