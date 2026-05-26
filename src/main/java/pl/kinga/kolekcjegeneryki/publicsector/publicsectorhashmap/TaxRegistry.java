package pl.kinga.kolekcjegeneryki.publicsector.publicsectorhashmap;

import java.util.HashMap;
import java.util.Map;

public class TaxRegistry {
    private Map<String, TaxpayerInfo> taxpayers;

    public TaxRegistry() {
        this.taxpayers = new HashMap<>();
    }

    public void registerTaxpayer(String nip, TaxpayerInfo info) {
        TaxpayerInfo taxpayerInfo = taxpayers.putIfAbsent(nip, info);
        if (taxpayerInfo != null) {
            System.out.println("Taxpayer with this nip number already exists in registry!");
        }
    }

    public void updateTaxpayer(String nip, TaxpayerInfo info) {
        TaxpayerInfo taxpayerInfo = taxpayers.replace(nip, info);
        if (taxpayerInfo == null) {
            System.out.println("Taxpayer with this nip number does not exist in registry!");
        }
    }

    public TaxpayerInfo getTaxpayer(String nip) {
        return taxpayers.get(nip);
    }

    public void removeTaxpayer(String nip) {
        taxpayers.remove(nip);
    }

    public boolean hasTaxpayer(String nip) {
        return taxpayers.containsKey(nip);
    }

    public int getTaxpayerCount() {
        return taxpayers.size();
    }

    public void printAllTaxpayers() {
        for (Map.Entry<String, TaxpayerInfo> entry : taxpayers.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
