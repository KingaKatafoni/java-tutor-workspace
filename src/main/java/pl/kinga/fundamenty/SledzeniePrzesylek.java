package pl.kinga.fundamenty;

public class SledzeniePrzesylek {
    public static void raportDostaw(String[] przesylki, String[] statusy){
        int licznikDostarczone = 0;
        int licznikWDrodze = 0;
        int licznikZwrot = 0;
        System.out.println("=== RAPORT DOSTAW ===");
        for (int i = 0; i < przesylki.length; i++) {

            System.out.println((i+1) + " " + przesylki[i] + " - " +statusy[i]);


            if (statusy[i].contains("DOSTARCZONA")){
                licznikDostarczone++;
            } else if (statusy[i].contains("W_DRODZE")) {
                licznikWDrodze++;
            } else if (statusy[i].contains("ZWROT")) {
                licznikZwrot++;
            }

        }System.out.println("--------------------");
        System.out.println("Dostarczone: " + licznikDostarczone );
        System.out.println("W drodze: " + licznikWDrodze );
        System.out.println("Zwroty: " + licznikZwrot );
        System.out.println("Łącznie: " + (licznikDostarczone + licznikWDrodze + licznikZwrot) );


    }

    static void main() {
        String[] przesylki = {"PKG-001", "PKG-002", "PKG-003", "PKG-004", "PKG-005"};
        String[] statusy = {"DOSTARCZONA", "W_DRODZE", "DOSTARCZONA", "ZWROT", "W_DRODZE"};
        raportDostaw(przesylki, statusy);
    }
}
