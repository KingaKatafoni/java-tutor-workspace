package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskA;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxSystemAdapterTest {

    @Test
    void shouldReturnTranslatedNameWhenFindTaxpayer() {
        LegacyTaxSystem legacyTaxSystem = new LegacyTaxSystem();
        TaxSystemAdapter adapter = new TaxSystemAdapter(legacyTaxSystem);

        TaxpayerInfo taxpayer = adapter.findTaxpayer("TP-001");

        assertEquals("Jan Kowalski", taxpayer.name());


    }

    @Test
    void shouldTranslateTaxId() {
        LegacyTaxSystem legacyTaxSystem = new LegacyTaxSystem();
        TaxSystemAdapter adapter = new TaxSystemAdapter(legacyTaxSystem);
        TaxpayerInfo taxpayer = adapter.findTaxpayer("TP-001");

        assertEquals("TP-001", taxpayer.taxId());
    }

    @Test
    void shouldCombineStreetAndCityIntoAddress() {
        LegacyTaxSystem legacyTaxSystem = new LegacyTaxSystem();
        TaxSystemAdapter adapter = new TaxSystemAdapter(legacyTaxSystem);
        TaxpayerInfo taxpayer = adapter.findTaxpayer("TP-001");

        assertEquals("ul. Dluga 5, Warszawa", taxpayer.address());
    }


}