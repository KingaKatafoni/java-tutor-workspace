package pl.kinga.fundamenty;

public class ZnajdzBledy {
    public static void main(String[] args) {
        // Kalkulator zdolnosci kredytowej
        double dochodMiesieczny = 6000.0;
        double wydatkiStale = 2500.0;
        int Wiek = 35;
        double maxRata;

        double nadwyzka;
        nadwyzka = dochodMiesieczny - wydatkiStale;
        maxRata = nadwyzka * 0.5;

        double kwotaKredytu = 0;
        kwotaKredytu =+ maxRata * 12 * 25;

        boolean czyPelnoletni = Wiek > 18;
        boolean czyMaZdolnosc = czyPelnoletni && (maxRata >= 500);

        System.out.println("Nadwyzka: " + nadwyzka);
        System.out.println("Max rata: " + maxRata);
        System.out.println("Kwota kredytu: " + kwotaKredytu);
        System.out.println("Pelnoletni: " + czyPelnoletni);
        System.out.println("Ma zdolnosc: " + czyMaZdolnosc);

        // linia 9: zmienne piszemy z małej litery powinno być int wiek = 35;
        // linia 15: nie powinniśmy przypisywać wartość 0 do zmiennej kwota kredytu, powinniśmy to pole najpierw zainicjalizować, powinno być: double kwotaKredytu;
        // linia 16: zmiana znaku z =+ na +=
        // linia 18: nadal zmienna z małej i dodatkowo zamiast wiek > 18 to >= 18, ktoś kto ma równo 18 lat jest pełnoletni
        // linia 19: dla pewności dodałam nawias do drugiego warunku w &&
    }

}
