package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

       Polisa polisaKW = new Polisa("POL-2026/00451","Katarzyna", "Wisniewska", "na zycie", new BigDecimal("100000"), new BigDecimal("150"));
       Polisa polisaTN = new Polisa("POL-2026/00452","Tomasz", "Nowak", "komunikacyjna OC", new BigDecimal("50000"), new BigDecimal("89.90"));

       polisaKW.wyswietlPolise();
       polisaTN.wyswietlPolise();

       polisaKW.setSkladkaMiesieczna(new BigDecimal("175"));
       polisaTN.setKwotaUbezpieczenia(new BigDecimal("-500"));
       polisaTN.dezaktywujPolise();

       polisaKW.wyswietlPolise();
       polisaTN.wyswietlPolise();


    }

}
