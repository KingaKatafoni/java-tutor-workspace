package pl.kinga.oop.eurzad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DecyzjaAdministracyjna decyzjaAdministracyjna = new DecyzjaAdministracyjna("AD/098/001", LocalDate.now(), "Joanna Kulig", "Dokumenty sa wazne");
        ZaswiadczenieZameldowania zaswiadczenieZameldowania = new ZaswiadczenieZameldowania("ZZ/321/001", LocalDate.of(2026, 3, 14), "Adam Nawalka", "89121423450");
        ProtokolKontroliPodatkowej protokolKontroliPodatkowej = new ProtokolKontroliPodatkowej("PKP/123/001", LocalDate.of(2025, 12, 24), "Marcin Brzoza", "28392888292");

        System.out.println(decyzjaAdministracyjna.getInfo());
        System.out.println(zaswiadczenieZameldowania.getInfo());
        System.out.println(protokolKontroliPodatkowej.getInfo());

        List<Drukowalny> printablowane = new ArrayList<>();
        printablowane.add(decyzjaAdministracyjna);
        printablowane.add(zaswiadczenieZameldowania);
        printablowane.add(protokolKontroliPodatkowej);

        System.out.println("-----Drukowalny wydruk-----");

        for (Drukowalny druk : printablowane) {
            System.out.println(druk.drukuj());
        }

        List<Archiwizowany> archiwalne = new ArrayList<>();
        archiwalne.add(decyzjaAdministracyjna);
        archiwalne.add(protokolKontroliPodatkowej);

        System.out.println("-----Archiwizowane-----");
        for (Archiwizowany archiwum : archiwalne){
            System.out.println(archiwum.archiwizuj());
        }


    }
}
