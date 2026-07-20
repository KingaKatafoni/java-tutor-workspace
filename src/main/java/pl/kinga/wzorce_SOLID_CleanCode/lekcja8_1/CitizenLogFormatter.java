package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

public class CitizenLogFormatter {

    public String formatLog(Citizen citizen) {
        return String.format("[%s] Registered: %s %s (PESEL: %s, age: %d, city: %s)",
                java.time.LocalTime.now(), citizen.firstName(), citizen.lastName(), citizen.pesel(), citizen.age(), citizen.city());
    }
}
