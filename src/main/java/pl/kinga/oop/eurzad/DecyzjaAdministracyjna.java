package pl.kinga.oop.eurzad;

import java.time.LocalDate;

public class DecyzjaAdministracyjna extends DokumentUrzedowy implements Drukowalny, Archiwizowany, PodpisywalnyCyfrowo{

    private String tresc;

    public DecyzjaAdministracyjna(String sygnatura, LocalDate dataUtworzenia, String autor, String tresc) {
        super(sygnatura, dataUtworzenia, autor);
        this.tresc = tresc;
    }

    @Override
    public String drukuj() {
        return "DRUK: Decyzja " + getSygnatura()+ ": " + tresc ;
    }

    @Override
    public String archiwizuj() {
        return "ARCHIWUM: Decyzja " + getSygnatura() + " zarchiwizowana";
    }

    @Override
    public boolean podpiszCyfrowo(String certyfikat) {
        return certyfikat != null && !certyfikat.isEmpty();
    }

    @Override
    public String getTypDokumentu() {
        return "Decyzja administracyjna";
    }
}
