package pl.kinga;

import java.util.Arrays;

public class AnalizaZuzycia {

    public static void analizuj(String klient, int[] zuzycieMB, int limitMB){
        StringBuilder info = new StringBuilder();
        String [] miesiace = {"Styczen", "Luty", "Marzec", "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien", "Pazdziernik", "Listopad", "Grudzien"};
        int licznikPowtorzen = 0;
        int max = zuzycieMB[0];
        int suma = 0;
        int srednia = 0;
        int pozycjaMiesiaca = 0;
        info
                .append("=== ANALIZA ZUZYCIA DANYCH ===\n")
                .append("Klient: ")
                .append(klient)
                .append(" | Limit: ")
                .append(limitMB)
                .append(" MB/mies.")
                .append("\n------------------------\n");
        for (int i = 0; i < 12; i++) {
            String aktualnyMiesiac = miesiace[i];
            int aktualneZuzycie = zuzycieMB[i];

            info.append(aktualnyMiesiac)
                    .append(": " )
                    .append(aktualneZuzycie)
                    .append(" MB");
            suma += zuzycieMB[i];
            srednia = suma /12 ;

            if (zuzycieMB[i] > max){
                max = zuzycieMB[i];
                pozycjaMiesiaca = i;
            }
            if (zuzycieMB[i] > limitMB){
                info.append(" *PRZEKROCZENIE*");
                licznikPowtorzen++;
            }
            info.append("\n");

        }
        info.append("------------------------\n")
                .append("Zuzycie roczne: ")
                .append(suma)
                .append(" MB\n")
                .append("Srednia miesieczna: ")
                .append(srednia)
                .append(" MB\n")
                .append("Najwieksze zuzycie: ")
                .append(miesiace[pozycjaMiesiaca])
                .append(" (")
                .append(max)
                .append(" MB)\nPrzekroczenia limitu: ")
                .append(licznikPowtorzen)
                .append(" razy")
                .append("\n");

        if (licznikPowtorzen > 3) {
            info.append("REKOMENDACJA: rozważ wyzszy pakiet danych!");
        }

        System.out.println(info);
    }

    static void main() {
        int[] zuzycie = {2100, 1800, 3200, 2500, 4100, 3800, 2900, 5200, 3100, 2700, 4500, 3600};
        analizuj("Kinga", zuzycie, 3000);
    }
}
