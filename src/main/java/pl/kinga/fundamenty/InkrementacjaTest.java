package pl.kinga.fundamenty;

public class InkrementacjaTest {
    public static void main(String[] args) {
        int x = 10;
        int y = 10;

        int a = x++;
        int b = ++y;

        System.out.println("x = " + x); // 11 czyli że inna zmienna korzysta z inkrementacji ona wpływa na oryginalną zmienną czyli to oznacza że zmieniliśmy inkrementacją jej wartosć w całym scope?
        System.out.println("y = " + y); // 11
        System.out.println("a = " + a); // 10 różnią się przez pre i post incrementację, w pre najpierw przypisujemy wartość a później inkremenrujemy a post najpiew inkrementujemy a później przypisujemy
        System.out.println("b = " + b); // 11
    }
}
