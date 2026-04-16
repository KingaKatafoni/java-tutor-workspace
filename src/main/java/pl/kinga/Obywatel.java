package pl.kinga;

import java.math.BigDecimal;

public class Obywatel {
    String PESEL; // faktycznie lepiej chyba pasuje String bo nie musimy wykonywać operacji matematycznych
    String imie; // standardowy string
    int wiek;//wiek może być prymitywem bo operujemy liczbami od 1 do max 120
    boolean isZameldowany; // prymityw bo jest adnotacja że zawsze wiadomo czy osoba jest zzmeldowana czy nie, wiec nie bedzie bull
    Integer dzieci;// tu wrapper bo jest adnotacja że obywatel może nie podać więc wystapi null
    BigDecimal dochodRoczny; // faktycznie BigDecimal będzie lepszy
    char plec; // tutaj mamy tylko dwie opcje nulla brak
}