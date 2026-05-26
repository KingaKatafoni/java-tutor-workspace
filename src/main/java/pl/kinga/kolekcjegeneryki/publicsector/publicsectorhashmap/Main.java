package pl.kinga.kolekcjegeneryki.publicsector.publicsectorhashmap;

public class Main {
    public static void main (String[] args) {
        TaxpayerInfo taxpayerInfo1 = new TaxpayerInfo("VW", "Poznanska 31a", 33900000.00);
        TaxpayerInfo taxpayerInfo2 = new TaxpayerInfo("Polbruk", "Wichrowe Wzgorza 12", 3500000.00);
        TaxpayerInfo taxpayerInfo3 = new TaxpayerInfo("Goplana", "Zasady 3", 900000.00);
        TaxpayerInfo taxpayerInfo4 = new TaxpayerInfo("Ametyst", "Herbowa 12", 300000.00);

        TaxRegistry taxRegistry = new TaxRegistry();

        taxRegistry.registerTaxpayer("1234567890", taxpayerInfo1);
        taxRegistry.registerTaxpayer("2345678901", taxpayerInfo2);
        taxRegistry.registerTaxpayer("3456789012", taxpayerInfo3);
        taxRegistry.registerTaxpayer("1234567890", taxpayerInfo4);

        taxRegistry.updateTaxpayer("4567890123", taxpayerInfo2);

        taxRegistry.printAllTaxpayers();
        System.out.println(taxRegistry.hasTaxpayer("1234567890"));
        System.out.println(taxRegistry.getTaxpayer("1234567890"));
    }
}
