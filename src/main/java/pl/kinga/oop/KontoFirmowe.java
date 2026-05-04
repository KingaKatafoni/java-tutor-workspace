package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class KontoFirmowe extends KontoBankowe {
    private BigDecimal limitKredytowy;

    public KontoFirmowe() {
    }

    public KontoFirmowe(String numerKonta, String wlasciciel, BigDecimal saldo, BigDecimal limitKredytowy) {
        super(numerKonta, wlasciciel, saldo);
        this.limitKredytowy = limitKredytowy;
    }

    public BigDecimal getLimitKredytowy() {
        return limitKredytowy;
    }

    @Override
    public BigDecimal obliczOdsetkiRoczne() {
        BigDecimal odsetkiRoczne = (getSaldo().multiply(new BigDecimal("0.02"))).setScale(2, RoundingMode.HALF_UP);
        if (getSaldo().compareTo(new BigDecimal("10000")) < 0) {
            return BigDecimal.ZERO;
        } else {
            return odsetkiRoczne;
        }
    }

    @Override
    public String info(){
        return "[FIRMOWE] "
                + getNumerKonta()
                + " - "
                + getWlasciciel()
                + " saldo: "
                + getSaldo()
                + "zl";
    }
}
