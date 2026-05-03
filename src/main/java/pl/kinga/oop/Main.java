package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Urzednik urzednik = new Urzednik("Katarzyna", "Figura", "PO-9087", new BigDecimal("40.50"), "Wydzial Komunikacji");

        Kierownik kierownik = new Kierownik("Bogdan", "Moscicki", "PY-5436", new BigDecimal("65.00"), new BigDecimal("600.00"));

        System.out.println(urzednik.przedstawSie());
        System.out.println(kierownik.przedstawSie());

        BigDecimal wynagrodzenieKierownika = kierownik.obliczWynagrodzenieMiesieczne(160);
        BigDecimal wynagrodzenieUrzednika = urzednik.obliczWynagrodzenieMiesieczne(160);

        if (wynagrodzenieKierownika.compareTo(wynagrodzenieUrzednika) > 0){
            System.out.println("Kierownik zarabia więcej");
        } else if (wynagrodzenieKierownika.compareTo(wynagrodzenieUrzednika) == 0) {
            System.out.println("Urzednik i kierownik zarabiaja tyle samo");
        } else {
            System.out.println("Urzednik zarabia wiecej");
            }

        PracownikUrzedu[] pracownicyUrzedu = {
                new PracownikUrzedu("Jan", "Niezbędny", "KL-234", new BigDecimal("90.50")),
                new Kierownik("Paulina", "Czapska", "WE-765", new BigDecimal("35.60"), new BigDecimal("450.00")),
                new Urzednik("Kamil", "Wrona", "PO-098", new BigDecimal("59.50"), "Wydzial Administracji")
        };

        for (PracownikUrzedu pracownik : pracownicyUrzedu) {
            System.out.println(
                    "Typ: " +
                            pracownik.getTypPracownika()
                            + ", " +
                            pracownik.getImie()
                            + " " +
                            pracownik.getNazwisko()
            );
        }


    }

}
