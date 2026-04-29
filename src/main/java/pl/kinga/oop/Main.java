package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
       ProduktSklepowy produkt1 = new ProduktSklepowy("Klawiatura mechaniczna", "Elektronika",  new BigDecimal("199.99"),  15);
       ProduktSklepowy produkt2 = new ProduktSklepowy("Koszulka polo", "Odziez", new BigDecimal("79.90"), 120);
       ProduktSklepowy produkt3 = new ProduktSklepowy("Ksiazka Java w akcji", "Ksiazki", new BigDecimal("65.00"), 0);

       ProduktSklepowy produkt4 = new ProduktSklepowy("Zasilacz USB-C", new BigDecimal("49.99"));

       produkt1.wyswietlProdukt();
       produkt2.wyswietlProdukt();
       produkt3.wyswietlProdukt();
       produkt4.wyswietlProdukt();

       System.out.println("Laczna liczba produktow: " + ProduktSklepowy.getLiczbaProduktow());
       produkt1.setCenaNetto(new BigDecimal("179.99"));
       produkt3.setStanMagazynowy(-5);

       produkt1.wyswietlProdukt();
       produkt3.wyswietlProdukt();
    }

}
