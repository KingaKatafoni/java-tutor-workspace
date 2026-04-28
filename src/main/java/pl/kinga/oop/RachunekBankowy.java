package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RachunekBankowy {
    String numerRachunku;
    String wlasciciel;
    String typRachunku;
    BigDecimal saldo;


    public RachunekBankowy() {
    }

    public RachunekBankowy(String numerRachunku, String wlasciciel, String typRachunku, BigDecimal saldo) {
        this.numerRachunku = numerRachunku;
        this.wlasciciel = wlasciciel;
        this.typRachunku = typRachunku;
        this.saldo = saldo;
    }

    public RachunekBankowy(String numerRachunku, String wlasciciel, BigDecimal saldo) {
        this.numerRachunku = numerRachunku;
        this.wlasciciel = wlasciciel;
        this.saldo = saldo;
        this.typRachunku = "osobiste";
    }

    public void wyswietlInformacje() {
        StringBuilder info = new StringBuilder();
        info.append("\n=== Rachunek bankowy ===")
                .append("\nNumer: ")
                .append(this.numerRachunku)
                .append("\nWlasciciel: ")
                .append(this.wlasciciel)
                .append("\nTyp: ")
                .append(this.typRachunku)
                .append("\nSaldo: ")
                .append(this.saldo);

        System.out.println(info);
    }

    public void wplata(BigDecimal kwota) {
        this.saldo = (saldo.add(kwota)).setScale(2, RoundingMode.HALF_UP);
        StringBuilder infoWplata = new StringBuilder();
        infoWplata.append("\nWplata ")
                .append(kwota)
                .append(" zl na rachunek ")
                .append(this.numerRachunku)
                .append(". \nNowe saldo ")
                .append(this.saldo);
        System.out.println(infoWplata);
    }

    public void wyplata(BigDecimal kwota) {
        if (kwota.compareTo(saldo) > 0) {
            System.out.println("\nBrak srodkow na rachunku! ");
        } else {
            this.saldo = (saldo.subtract(kwota)).setScale(2, RoundingMode.HALF_UP);
            StringBuilder infoWyplata = new StringBuilder();
            infoWyplata.append("\nWyplata ")
                    .append(kwota)
                    .append(" zl z rachunku ")
                    .append(this.numerRachunku)
                    .append(". \nNowe saldo ")
                    .append(this.saldo);
            System.out.println(infoWyplata);
        }

    }


}
