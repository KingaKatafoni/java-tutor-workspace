package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskA;

public class TaxSystemAdapter implements TaxpayerProvider{
    private final LegacyTaxSystem legacyTaxSystem;

    public TaxSystemAdapter(LegacyTaxSystem legacyTaxSystem){
        this.legacyTaxSystem = legacyTaxSystem;
    }

    @Override
    public TaxpayerInfo findTaxpayer(String taxId) {
        LegacyTaxRecord legacyTaxRecord = legacyTaxSystem.fetchRecord(taxId);
        String address = legacyTaxRecord.street() + ", " + legacyTaxRecord.city();
        return new TaxpayerInfo(legacyTaxRecord.fullName(), taxId, address);
    }
}
