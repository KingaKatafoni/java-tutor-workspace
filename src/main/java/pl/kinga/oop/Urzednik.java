package pl.kinga.oop;

import java.math.BigDecimal;

public class Urzednik extends PracownikUrzedu{
    private String wydzial;

    public Urzednik(String imie, String nazwisko, String numerSluzby, BigDecimal stawkaGodzinowa, String wydzial){
        super(imie, nazwisko, numerSluzby, stawkaGodzinowa);
        this.wydzial = wydzial;
    }

    @Override
    public String przedstawSie() {

        return getImie() +
                " " +
                getNazwisko() +
                ", " +
                wydzial +
                ", numer sluzby: " +
                getNumerSluzby();
    }

    @Override
    public String getTypPracownika(){
        return "Urzednik";
    }
}
