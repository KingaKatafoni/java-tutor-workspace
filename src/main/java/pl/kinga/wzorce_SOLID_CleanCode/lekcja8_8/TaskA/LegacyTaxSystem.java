package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_8.TaskA;

public class LegacyTaxSystem {
    public LegacyTaxRecord fetchRecord(String id){
        return new LegacyTaxRecord(id, "Jan Kowalski", "ul. Dluga 5", "Warszawa");
    }
}
