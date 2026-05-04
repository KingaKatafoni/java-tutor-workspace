package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class KontoOszczednosciowe extends KontoBankowe {
    private BigDecimal oprocentowanie;

    public KontoOszczednosciowe(){}

    public KontoOszczednosciowe(String numerKonta, String wlasciciel, BigDecimal saldo, BigDecimal oprocentowanie){
        super(numerKonta, wlasciciel, saldo);
        this.oprocentowanie = oprocentowanie;
    }

    public BigDecimal getOprocentowanie(){
        return oprocentowanie;
    }

    @Override
    public BigDecimal obliczOdsetkiRoczne(){
        return (getSaldo().multiply(oprocentowanie)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String info(){
        return "[OSZCZEDNOSCIOWE] "
                + getNumerKonta()
                + " - "
                + getWlasciciel()
                + " saldo: "
                + getSaldo()
                + "zl";
    }
}
