package pl.kinga.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProduktSklepowy {
    public static final BigDecimal STAWKA_VAT = new BigDecimal("0.23");
    public static final String WALUTA = "PLN";
    private static int licznikProduktow = 0;
    private final String kodProduktu;
    private String nazwa;
    private String kategoria;
    private BigDecimal cenaNetto;
    private int stanMagazynowy;


    public ProduktSklepowy(String nazwa, String kategoria, BigDecimal cenaNetto, int stanMagazynowy) {
        licznikProduktow++;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cenaNetto = cenaNetto;
        this.stanMagazynowy = stanMagazynowy;
        this.kodProduktu = "PROD-" + licznikProduktow;
    }

    public ProduktSklepowy(String nazwa, BigDecimal cenaNetto) {
        this(nazwa, "Ogolne", cenaNetto, 0);
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public BigDecimal getCenaNetto() {
        return cenaNetto;
    }

    public int getStanMagazynowy() {
        return stanMagazynowy;
    }

    public boolean isNaMagazynie() {
        return stanMagazynowy > 0;
    }

    public String getKodProduktu() {
        return kodProduktu;
    }

    public void setNazwa(String nazwa) {
        if (nazwa == null || nazwa.isEmpty()) {
            System.out.println("Nazwa nie może być null lub pusta");
            return;
        }
        this.nazwa = nazwa;
    }

    public void setKategoria(String kategoria) {
        if (kategoria == null || kategoria.isEmpty()) {
            System.out.println("Kategoria nie może być null lub pusta");
            return;
        }
        this.kategoria = kategoria;
    }

    public void setCenaNetto(BigDecimal cena) {
        if (cena == null || cena.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Cena nie może być <= 0!");
            return;
        }
        this.cenaNetto = cena;
    }

    public void setStanMagazynowy(int stanMagazynowy) {
        if (stanMagazynowy < 0) {
            System.out.println("Stan magazynowy nie może być < 0!");
        } else {
            this.stanMagazynowy = stanMagazynowy;
        }
    }

    public BigDecimal getCenaBrutto() {
        BigDecimal cenaNettoZPodatkiem = cenaNetto.multiply(STAWKA_VAT);
        return (cenaNetto.add(cenaNettoZPodatkiem)).setScale(2, RoundingMode.HALF_UP);
    }

    public void wyswietlProdukt() {
        StringBuilder dane = new StringBuilder();
        String stawkaVAT = " (VAT " + STAWKA_VAT.multiply(new BigDecimal("100")) + "%)";
        dane.append(" === Produkt ===")
                .append("\nKod: ")
                .append(kodProduktu)
                .append("\nNazwa produktu: ")
                .append(nazwa)
                .append("\nKategoria: ")
                .append(kategoria)
                .append("\nCena netto: ")
                .append(cenaNetto)
                .append(" ")
                .append(WALUTA)
                .append("\nCena brutto: ")
                .append(" ")
                .append(getCenaBrutto())
                .append(WALUTA)
                .append(stawkaVAT)
                .append("\nMagazyn: ")
                .append(stanMagazynowy)
                .append("szt. (");

        if (stanMagazynowy == 0) {
            dane.append("BRAK)\n");
        } else {
            dane.append("DOSTEPNY)\n");
        }
        System.out.println(dane);
    }

    public static int getLiczbaProduktow() {
        return licznikProduktow;
    }
}
