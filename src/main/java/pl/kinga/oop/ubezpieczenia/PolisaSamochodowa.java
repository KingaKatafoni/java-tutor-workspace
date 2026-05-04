package pl.kinga.oop.ubezpieczenia;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PolisaSamochodowa extends Polisa {
    private int rokProdukcji;
    private final int ROK_BAZOWY = 2026;

    public PolisaSamochodowa() {
    }

    public PolisaSamochodowa(String numerPolisy, String ubezpieczony, BigDecimal kwotaBazowa, int rokProdukcji) {
        super(numerPolisy, ubezpieczony, kwotaBazowa);
        this.rokProdukcji = rokProdukcji;
    }

    @Override
    public BigDecimal obliczSkladke() {
        BigDecimal lataPojazdu = new BigDecimal(ROK_BAZOWY - rokProdukcji);
        if (rokProdukcji >= ROK_BAZOWY) {
            return getKwotaBazowa();
        }
        return (getKwotaBazowa().add(lataPojazdu.multiply(new BigDecimal("100")))).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String info(){
        return "[SAMOCHODOWA] "
                + getNumerPolisy()
                + " - "
                + getUbezpieczony();
    }
}
