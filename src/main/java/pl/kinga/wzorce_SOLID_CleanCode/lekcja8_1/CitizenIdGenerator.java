package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

public class CitizenIdGenerator {
    public String generateId(String pesel) {
        return "CIT-" + pesel.substring(0, 4) + "-" + System.currentTimeMillis() % 1000;
    }
}
