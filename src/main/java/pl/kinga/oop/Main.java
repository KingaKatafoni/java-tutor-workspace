package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
       System.out.println(NarzedziaOperatora.czyNumerPoprawny("501234567"));
       System.out.println(NarzedziaOperatora.czyNumerPoprawny("123456789"));
       System.out.println(NarzedziaOperatora.czyNumerPoprawny("50123"));

       System.out.println(NarzedziaOperatora.formatujNumer("501234567"));

       System.out.println(NarzedziaOperatora.obliczBrutto(new BigDecimal("49.99")));

       System.out.println(NarzedziaOperatora.obliczRabat(new BigDecimal(120), 20));

       System.out.println(NarzedziaOperatora.opisPakietu("Internet Max", new BigDecimal("49.99"), 50, 300));
       System.out.println(NarzedziaOperatora.opisPakietu("Starter", new BigDecimal("29.99"), 10, 100));
    }

}
