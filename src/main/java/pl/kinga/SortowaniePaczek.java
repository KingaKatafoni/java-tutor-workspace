package pl.kinga;

public class SortowaniePaczek {
    public static void sortujPaczki(double[] wagi){
        for (double wagaPaczki : wagi){
            if (wagaPaczki <= 0) {
                System.out.println("UWAGA: pominieto paczke o wadze " + wagaPaczki + " (bledne dane)");
                continue;
            } else if (wagaPaczki <= 5) {
                System.out.println( wagaPaczki + " kg" + " -> Kurier rowerowy");
            } else if ( wagaPaczki <= 15){
                System.out.println( wagaPaczki + " kg" + " -> Kurier samochodowy");
            } else {
                System.out.println( wagaPaczki + " kg" + " -> Kurier ciezarowy");
            }
        }
    }

    static void main() {
        double[] wagi = {2.5, 0, 8.3, 22.0, -1, 4.9, 15.1, 12.0};
        sortujPaczki(wagi);
    }
}
