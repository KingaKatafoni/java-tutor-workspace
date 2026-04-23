package pl.kinga;

import java.util.Arrays;

public class GrafikPrzychodni {

    public static void wyswietlGrafik(String[] lekarze, String[] dni, int[][] wizyty) {
        StringBuilder grafik = new StringBuilder();
        int suma = 0;
        int sumaPerDzien = 0;
        int[] sumyPerDzien = {0, 0, 0, 0, 0};
        int indexNajbardzieObciazonyDzien = 0;
        int indexNajbardziejObciazonyLekarz = 0;
        grafik.append("=== GRAFIK PRZYCHODNI ===\n")
                .append("Lekarz        ");
        for (String dzien : dni) {
            grafik.append(dzien)
                    .append(" ");
        }
        grafik.append("| SUMA");

        // # wypisywanie lekarzy i w petli zagniezdzonej sumowanie wizyt dla pojedynczego lekarza
        int[] sumaPerLekarz = new int[3];
        int licznikWizyt = 0;
        for (int i = 0; i < wizyty.length; i++) {

            grafik.append("\n")
                    .append(lekarze[i])
                    .append(": ");
            for (int j = 0; j < wizyty[i].length; j++) {
                licznikWizyt += wizyty[i][j];
                sumaPerLekarz[i] = licznikWizyt;
                grafik
                        .append(wizyty[i][j])
                        .append(" ");
            }

            grafik.append(" | ")
                    .append(licznikWizyt)
                    .append(" ");

            suma += licznikWizyt;
            licznikWizyt = 0;

        }

        grafik
                .append("\n---------------------------")
                .append("\nRAZEM ");
        // # sumowanie ilości pacjentów każdego dnia -> szukanie po kolumnach nie po wierszach
        for (int i = 0; i < wizyty[0].length; i++){
            for (int j = 0; j < wizyty.length; j++){
                sumaPerDzien += wizyty[j][i];
            }
            sumyPerDzien[i] = sumaPerDzien;
            grafik.append(sumaPerDzien)
                    .append(" ");
            sumaPerDzien = 0;
        }
 // #szukanie maksymalnej liczby pacjentów danego dnia
        int maxPerDzien = sumyPerDzien[0];
        for (int i = 0; i < sumyPerDzien.length; i++){
            if (sumyPerDzien[i] > maxPerDzien){
                maxPerDzien = sumyPerDzien[i];
                indexNajbardzieObciazonyDzien = i;
            }
        }
// # szukanie maksymalnej liczby pacjentow dla danego lekarza
        int maxPerLekarz = sumaPerLekarz[0];
        for (int i = 0; i < sumaPerLekarz.length; i++){
            if (sumaPerLekarz[i] > maxPerLekarz){
                maxPerLekarz = sumaPerLekarz[i];
                indexNajbardziejObciazonyLekarz= i;
            }
        }
                grafik.append(" | ")
                        .append(suma)
                        .append("\nNajbardziej obciazony dzien: ")
                        .append(dni[indexNajbardzieObciazonyDzien])
                        .append(" (")
                        .append(sumyPerDzien[indexNajbardzieObciazonyDzien])
                        .append(" wizyty)")
                        .append("\nNajwiecej wizyt: ")
                        .append(lekarze[indexNajbardziejObciazonyLekarz])
                        .append(" (")
                        .append(sumaPerLekarz[indexNajbardziejObciazonyLekarz])
                        .append(" wizyt/tydzien)");

        System.out.println(grafik);
    }

    static void main() {
        String[] lekarze = {"Dr Kowalski", "Dr Nowak", "Dr Wiśniewska"};
        String[] dni = {"Pon", "Wt", "Sr", "Czw", "Pt"};

        int[][] wizyty = {
                {8, 12, 6, 10, 9},
                {15, 8, 11, 14, 7},
                {10, 10, 10, 10, 10}
        };

        wyswietlGrafik(lekarze, dni, wizyty);

    }
}




