package pl.kinga.oop.platnosci;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OplataZaDokument extends OplataUrzedowa {
    private LocalDate termin;
    private static final BigDecimal PROCENT_KARY = new BigDecimal("0.1");

    public OplataZaDokument(BigDecimal kwota, LocalDate dataPlatnosci, String pesel, LocalDate termin) {
        super(kwota, dataPlatnosci, pesel);
        this.termin = termin;
    }

    @Override
    public String getOpis() {
        return "Oplata za dokument";
    }

    @Override
    public BigDecimal obliczKare() {
        LocalDate dzisiaj = LocalDate.now();
        long liczbaMiesiecy = ChronoUnit.MONTHS.between(termin, dzisiaj);
        if (liczbaMiesiecy <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal kara = getKwota().multiply(PROCENT_KARY.multiply(new BigDecimal(liczbaMiesiecy)));
        return kara.setScale(2, RoundingMode.HALF_UP);
    }
}
