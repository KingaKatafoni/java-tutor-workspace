package pl.kinga;

public class SymulatorBanku {

    public static void sprobujPodniescLimit(int limit){
        limit += 5000;
        System.out.println("Limit w metodzie: " + limit);
    }

    public static void dodajTransakcje(int[] historia, int indeks, int kwota){
        historia[indeks] = kwota;
    }

    public static void wyzerujHistorie(int[] historia){
        historia = new int[historia.length];
        System.out.println("Historia w metodzie: " + historia[0]);
    }

    public static void sprobujZmienicNazweKlienta(String nazwa){
        nazwa += " Premium";
        System.out.println("Wewnątrz metody nazwa: " + nazwa);
    }

    public static void main(String[] args){
        // --- Test 1: prymityw ---
        int limitKredytu = 10000;
        sprobujPodniescLimit(limitKredytu);
        System.out.println("Limit po metodzie: " + limitKredytu);
// BRAK ZMIANY: limitKredytu pozostał taki sam jak ten zadeklarowany i przekazany do metody, zwiększenie wewnątrz metody nie działa na zewnatrz metody na tą zmienna

        // --- Test 2: tablica — zmiana zawartosci ---
        int[] historia = {100, 200, 0, 0, 0};
        dodajTransakcje(historia, 2, 350);
        System.out.println("historia[2] po metodzie: " + historia[2]);
        System.out.println("historia[1] po metodzie: " + historia[1]);
// ZMIANA : Tutaj tablica zdefiniowana poza metodą została zmieniona wartość kwoty przekazanej do metody została zapisana w tablicy.
// Bo tablica nie jest kopiowana jak wartość prymitywna jest ta sama referencja wskazująca na tę samą tablicę


// --- Test 3: tablica — podmiana referencji ---
        int[] historia2 = {500, 600, 700};
        wyzerujHistorie(historia2);
        System.out.println("historia2[0] po metodzie: " + historia2[0]);
// BRAK ZMIANY: W metodzie jest tworzona nowa tablica int skladajaca sie domyslnie z 0 wiec w metodzie dostaniemy dla elementu historia2[0] 0, a po metodzie pozostaje wartosc z przekazanej do metody tablicy a nie wyzerowana.
// Dlaczego? Bo tworzony jest nowy obiekt i referencje sie wskazuja na ten sam element

        // --- Test 4: String ---
        String klient = "Anna Nowak";
        sprobujZmienicNazweKlienta(klient);
        System.out.println("Klient po metodzie: " + klient);
// BRAK ZMIANY: Tutaj jest podobna sytuacja w metodzie jest tworzony nowy obiekt String, więc ten String z metody jest inny niż ten przekazywany

    }


}
