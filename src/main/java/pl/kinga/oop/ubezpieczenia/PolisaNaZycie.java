package pl.kinga.oop.ubezpieczenia;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PolisaNaZycie extends Polisa {
    private int wiekUbezpieczonego;

    public PolisaNaZycie() {
    }

    public PolisaNaZycie(String numerPolisy, String ubezpieczony, BigDecimal skladkaBazowa, int wiekUbezpieczonego) {
        super(numerPolisy, ubezpieczony, skladkaBazowa);
        this.wiekUbezpieczonego = wiekUbezpieczonego;
    }

    @Override
    public BigDecimal obliczSkladke() {
        BigDecimal wspolczynnik;
        if (wiekUbezpieczonego < 30) {
            wspolczynnik = new BigDecimal("1.0");
        } else if (wiekUbezpieczonego <= 50) {
            wspolczynnik = new BigDecimal("1.5");
        } else {
            wspolczynnik = new BigDecimal("2.0");
        }
        return (getKwotaBazowa().multiply(wspolczynnik)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String info(){
        return "[NA ZYCIE] "
                + getNumerPolisy()
                + " - "
                + getUbezpieczony();
    }
}
