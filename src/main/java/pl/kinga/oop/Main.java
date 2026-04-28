package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        RachunekBankowy rachunekBankowyAnna = new RachunekBankowy("PL61 1090 0014 0000 0712 1981 0001",
                "Anna Kowalska", new BigDecimal("5000"));

        RachunekBankowy rachunekBankowyJan = new RachunekBankowy("PL61 1090 0014 0000 0712 1981 0002", "Jan Kowalski", "firmowe", new BigDecimal("120000"));

        rachunekBankowyAnna.wyswietlInformacje();
        rachunekBankowyJan.wyswietlInformacje();

        rachunekBankowyAnna.wplata(new BigDecimal("2500"));
        rachunekBankowyJan.wyplata(new BigDecimal("45000"));

        rachunekBankowyAnna.wyplata(new BigDecimal("10000"));

        rachunekBankowyAnna.wyswietlInformacje();
        rachunekBankowyJan.wyswietlInformacje();


    }

}
