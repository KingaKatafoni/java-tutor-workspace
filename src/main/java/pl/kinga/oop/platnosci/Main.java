package pl.kinga.oop.platnosci;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<OplataUrzedowa> listaOplat = new ArrayList<>();

        listaOplat
                .add(new OplataZaDokument(
                        new BigDecimal("60"), LocalDate.of(2026, 5, 5), "89204590543", LocalDate.of(2026, 6, 13)));

        listaOplat
                .add(new OplataZaDokument(
                        new BigDecimal("150"), LocalDate.of(2026, 5, 5), "98234509543", LocalDate.of(2026, 2, 13)));

        listaOplat
                .add(new PodatekOdNieruchomosci(
                        new BigDecimal("115.50"), LocalDate.of(2026, 5, 5), "56234509543", LocalDate.of(2025, 6, 25)));

        for (OplataUrzedowa oplata : listaOplat) {
            System.out.println(oplata.generujPotwierdzenie());
            System.out.println("------KARA-------");
            System.out.println(oplata.obliczKare() + " zl");
            System.out.println("-----------------");
        }
    }
}
