package pl.kinga;

public class KalkulatorOplatUrzedowych {
    public static int obliczOplateZaDokument(String typDokumentu){
        int kwota = switch (typDokumentu){
            case "dowod" -> 30;
            case "paszport" -> 140;
            case "odpis_aktu_urodzenia" -> 22;
            case "zaswiadczenie" -> 17;
            default -> 0;
        };
        return kwota;
    }

    public static boolean czyZwolnionyZOplaty(int wiek){

        return wiek < 18 || wiek >= 75;
    }

    public static int obliczKwoteKoncowa(String typDokumentu, int wiek, boolean ekspresowo){
        boolean czyZwolniony = czyZwolnionyZOplaty(wiek);
        int kwota = obliczOplateZaDokument(typDokumentu);
        if (czyZwolniony) {
            return 0;
        }
        if (ekspresowo) {
            return kwota * 2;
        }
        return kwota;


    }

    public static void wyswietlParagon(String imie, String typDokumentu, int wiek, boolean ekspresowo){
        StringBuilder paragon = new StringBuilder();
        paragon
                .append("=== PARAGON ===\n")
                .append("Obywatel: ")
                .append(imie)
                .append("\nDokument: ")
                .append(typDokumentu)
                .append("\nTryb: ");
        if (ekspresowo){
            paragon.append("EKSPRESOWO");
        } else {
            paragon.append("normalny");
        }
        paragon.append("\nKwota: ")
                .append(obliczKwoteKoncowa(typDokumentu, wiek, ekspresowo))
                .append(" zl");
        if (czyZwolnionyZOplaty(wiek)){
            paragon.append(" (zwolniony z opłaty)");
        }
        paragon.append("\n===============\n");
        System.out.println(paragon);
    }

    static void main() {
       wyswietlParagon("Jan Kowalski", "paszport", 35, true);
       wyswietlParagon("Anna Nowak", "dowod", 16, false);
       wyswietlParagon("Stanislaw Wisniewski", "odpis_aktu_urodzenia", 78, false);
    }

}



