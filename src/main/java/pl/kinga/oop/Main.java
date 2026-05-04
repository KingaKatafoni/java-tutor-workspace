package pl.kinga.oop;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        KontoBankowe[] kontaBankowe = {
                new KontoBankowe("PL-3456-K", "Jan Niezbedny", new BigDecimal("12000")),
                new KontoOszczednosciowe("PL-235-F", "Kinga Binga", new BigDecimal("150000"), new BigDecimal("0.06")),
                new KontoFirmowe("PL-9003_F", "Krystian Pudzian", new BigDecimal("5600"), new BigDecimal("4000"))
        };

        for (KontoBankowe konto : kontaBankowe){
            System.out.println(konto.info());
            System.out.println("Odsetki roczne konta " + konto.getNumerKonta() + ": " +konto.obliczOdsetkiRoczne() + "zl");
        }

        System.out.println("Suma odsetek dla wszytskich kont: " + sumaOdsetek(kontaBankowe) + " zl");


    }

    public static BigDecimal sumaOdsetek(KontoBankowe[] konta){
        BigDecimal sumaOdsetek = new BigDecimal("0");
        for (int i = 0; i < konta.length; i++ ) {
            BigDecimal odsetki = konta[i].obliczOdsetkiRoczne();
            sumaOdsetek = sumaOdsetek.add(odsetki);
        }
        return sumaOdsetek;

        //**Pytanie:** Czy musisz zmieniac metode `sumaOdsetek()`? Dlaczego tak lub nie?
        // Nie musze zmieniac metody sumaOdsetek, poniewaz jest ona uniwersalna i zmienne na ktorych dzialam nie sa przypisane na sztywno.
        // Jezeli po miesiacu dodane zostanie kolejny rodzaj konta to do tablicy typu KontoBankowe dodam kolejny obiekt typu KontoStudenckie i metoda sumaOdsetek zwroci sume powiekszona o odsetki z konta studenckiego.
    }

}
