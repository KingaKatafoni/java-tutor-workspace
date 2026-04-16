package pl.kinga;

public class PorownanieTest {
    public static void main(String[] args) {
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;

        System.out.println("a == b: " + (a == b));
        System.out.println("c == d: " + (c == d));


        // Dodaj ponizej porownanie c i d przez .equals()
        System.out.println("c.equals(d): " + c.equals(d));
        // i wypisz wynik
        //c.equals(d): true
        // wyniki są różne ponieważ w c==d Java tworzy nowe obiekty, bo liczba 200 > 127 a do 127 java cachuje obiekty Integer, powyżej są porównywane referencje nie wartości
        // a c.equals(d) porównuje wartości
    }
}
