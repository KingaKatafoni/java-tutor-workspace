package pl.kinga.fundamenty;

public class NullTest {
    public static void main(String[] args) {
        Integer liczbaDzieci = null;  // obywatel nie podal
        int dzieci;
        // Odkomentuj linie ponizej, uruchom i zobacz co sie stanie:
        if (liczbaDzieci != null){
            dzieci = liczbaDzieci;
        } else {
            dzieci = 0;
        }
        System.out.println("Dzieci: " + dzieci);

        // Jak mozna sie zabezpieczyc przed tym bledem?
        //Można albo użyć unboxingu dla int dzieci albo można opakować int dzieci warunkiem if
        // Napisz wersje z "if" ktora sprawdza czy liczbaDzieci nie jest null
    }
}
