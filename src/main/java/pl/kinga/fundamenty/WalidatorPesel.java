package pl.kinga.fundamenty;

public class WalidatorPesel {


public static String walidujPesel(String pesel) {
    String info;
    boolean czyWszytskieCyfry = true;


    if (pesel == null) {
        return "Blad: PESEL nie może być pusty";
    } else if (pesel.trim().length() != 11) {
        return "Blad: PESEL musi miec 11 znakow (podano: " + pesel.trim().length() + ")";
    } else {
        for (int i = 0; i < pesel.trim().length(); i++) {
            if (!Character.isDigit(pesel.trim().charAt(i))) {
                return "Blad: PESEL moze zawierac tylko cyfry";
            }
        }

        return "PESEL poprawny (po walidacji formatu)";

    }

}

    public static void main(String[] args) {
        System.out.println(walidujPesel(null));
        System.out.println(walidujPesel("  "));
        System.out.println(walidujPesel("1234567890"));
        System.out.println(walidujPesel("1234567890a"));
        System.out.println(walidujPesel("12345678901"));
        System.out.println(walidujPesel(" 12345678901 "));

    }


}
