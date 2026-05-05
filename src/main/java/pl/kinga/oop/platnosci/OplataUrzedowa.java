package pl.kinga.oop.platnosci;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class OplataUrzedowa {
    private BigDecimal kwota;
    private LocalDate dataPlatnosci;
    private String platnikPesel;

    public OplataUrzedowa(BigDecimal kwota, LocalDate dataPlatnosci, String platnikPesel){
        this.kwota = kwota;
        this.dataPlatnosci = dataPlatnosci;
        this.platnikPesel = platnikPesel;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public LocalDate getDataPlatnosci() {
        return dataPlatnosci;
    }

    public String getPlatnikPesel(){
        return platnikPesel;
    }

    public abstract String getOpis();

    public abstract BigDecimal obliczKare();

    public String generujPotwierdzenie(){
        return "["
                + dataPlatnosci
                + "] | ["
                + platnikPesel
                + "] | ["
                + getOpis()
                + "] | ["
                + kwota
                + "] PLN";
    }
}
