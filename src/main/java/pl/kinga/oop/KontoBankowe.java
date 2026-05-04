package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class KontoBankowe {
    private String numerKonta;
    private String wlasciciel;
    private BigDecimal saldo;
    private final BigDecimal STAWKA_BAZOWA = new BigDecimal("0.01").setScale(2, RoundingMode.HALF_UP);

    public KontoBankowe() {
    }

    public KontoBankowe(String numerKonta, String wlasciciel, BigDecimal saldo) {
        this.numerKonta = numerKonta;
        this.wlasciciel = wlasciciel;
        this.saldo = saldo;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public BigDecimal getSaldo(){
        return saldo;
    }

    public BigDecimal obliczOdsetkiRoczne() {
        return (saldo.multiply(STAWKA_BAZOWA)).setScale(2, RoundingMode.HALF_UP);
    }

    public String info() {
        return "[KONTO] "
                + numerKonta
                + " - "
                + wlasciciel
                + " saldo: "
                + saldo
                + "zl";
    }


}
