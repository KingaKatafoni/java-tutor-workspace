package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

public class ConfirmationFormatter {

    public String formatConfirmation(Citizen citizen, String idNumber) {

        return String.format(
                "Potwierdzenie rejestracji\n" +
                        "========================\n" +
                        "Imie: %s\nNazwisko: %s\nPESEL: %s\nWiek: %d\nMiasto: %s\n" +
                        "Numer identyfikacyjny: %s", citizen.firstName(), citizen.lastName(), citizen.pesel(), citizen.age(), citizen.city(), idNumber
        );
    }
}
