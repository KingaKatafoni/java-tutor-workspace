package pl.kinga.kolekcjegeneryki.publicsector.publicsectorhashmap;

public record TaxpayerInfo(String companyName, String address, double annualRevenue) {

    public TaxpayerInfo {
        if (companyName == null || companyName.isEmpty() || address == null || address.isEmpty() || annualRevenue < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }

    }
}
