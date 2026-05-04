package pl.kinga.oop.ubezpieczenia;

import java.math.BigDecimal;

public class Polisa {
    private String numerPolisy;
    private String ubezpieczony;
    private BigDecimal kwotaBazowa;

    public Polisa(){}

    public Polisa(String numerPolisy, String ubezpieczony, BigDecimal kwotaBazowa){
        this.numerPolisy = numerPolisy;
        this.ubezpieczony = ubezpieczony;
        this.kwotaBazowa = kwotaBazowa;
    }

    public Polisa(String numerPolisy, String ubezpieczony){
        this.numerPolisy = numerPolisy;
        this.ubezpieczony = ubezpieczony;
        this.kwotaBazowa = BigDecimal.ZERO;
    }

    public BigDecimal obliczSkladke(){
        return kwotaBazowa;
    }

    public String info(){
        return "[POLISA] "
        + numerPolisy
        + " - "
        + ubezpieczony;
    }

    public String info(boolean szczegolowa){
        if (szczegolowa){
            return info()
                    + " skladka: "
                    + obliczSkladke()
                    + " zl ";
        }
            return info();
    }

    public String getNumerPolisy(){
        return numerPolisy;
    }

    public String getUbezpieczony(){
        return ubezpieczony;
    }

    public BigDecimal getKwotaBazowa(){
        return kwotaBazowa;
    }
}
