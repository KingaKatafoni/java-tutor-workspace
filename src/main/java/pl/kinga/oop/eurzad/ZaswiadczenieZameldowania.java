package pl.kinga.oop.eurzad;

import java.time.LocalDate;

public class ZaswiadczenieZameldowania extends DokumentUrzedowy implements Drukowalny {
    private String peselMieszkanca;

    public ZaswiadczenieZameldowania(String sygnatura, LocalDate dataUtworzenia, String autor, String peselMieszkanca) {
        super(sygnatura, dataUtworzenia, autor);
        this.peselMieszkanca = peselMieszkanca;
    }

    @Override
    public String drukuj() {
        return "DRUK: Zaswiadczenie o zameldowaniu dla PESEL " + peselMieszkanca;
    }

    @Override
    public String getTypDokumentu() {
        return "Zaswiadczenie o zameldowaniu";
    }


}
