package pl.kinga.oop.platnosci;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PodatekOdNieruchomosci extends OplataUrzedowa {
    private LocalDate termin;
    public static final BigDecimal STALA_KWOTA = new BigDecimal("50.00");
    public static final BigDecimal PROCENT_ZA_ZWLOKE = new BigDecimal("0.01");

    public PodatekOdNieruchomosci(BigDecimal kwota, LocalDate dataPlatnosci, String pesel, LocalDate termin) {
        super(kwota, dataPlatnosci, pesel);
        this.termin = termin;
    }

    @Override
    public String getOpis() {
        return "Oplata za podatek od nieruchomosci";
    }

    @Override
    public BigDecimal obliczKare() {
        LocalDate dzisiaj = LocalDate.now();
        long liczbaMiesiecy = ChronoUnit.MONTHS.between(termin, dzisiaj);
        if (liczbaMiesiecy <=0 ){
            return BigDecimal.ZERO;
        }
        return (STALA_KWOTA.add(getKwota().multiply(PROCENT_ZA_ZWLOKE).multiply(new BigDecimal(liczbaMiesiecy)))).setScale(2, RoundingMode.HALF_UP);
    }
}
