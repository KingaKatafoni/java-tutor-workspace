package pl.kinga;

public class KlasyfikatorPolisy {
    public static String klasyfikuj(String kodPolisy){
        String opis = switch (kodPolisy){
            case "OC" -> "Obowiazkowe ubezpieczenie komunikacyjne";
            case "AC" -> "Dobrowolne ubezpieczenie autocasco";
            case "NNW" -> "Ubezpieczenie nastepstw nieszczesliwych wypadkow";
            case "NA_ZYCIE", "ZYCIE" -> "Ubezpieczenie na zycie";
            case "DOM", "MIESZKANIE" -> "Ubezpieczenie nieruchomosci";
            default -> "Nieznany typ polisy: " + kodPolisy;
        };
        return opis;
    }

    static void main() {
        System.out.println(klasyfikuj("OC"));
        System.out.println(klasyfikuj("BB"));
        System.out.println(klasyfikuj("MIESZKANIE"));
    }
}
