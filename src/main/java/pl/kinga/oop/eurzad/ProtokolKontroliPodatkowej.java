package pl.kinga.oop.eurzad;

import java.time.LocalDate;
import java.util.Locale;

public class ProtokolKontroliPodatkowej extends DokumentUrzedowy implements Drukowalny, Archiwizowany {
    private String nip;

    public ProtokolKontroliPodatkowej(String sygnatura, LocalDate dataUtworzenia, String autor, String nip) {
        super(sygnatura, dataUtworzenia, autor);
        this.nip = nip;
    }

    @Override
    public String drukuj() {
        return "DRUK: Protokol kontroli NIP " + nip + "";
    }

    @Override
    public String archiwizuj() {
        return "ARCHIWUM: Protokol " + getSygnatura() + " — kontrola NIP  " + nip;
    }

    @Override
    public String getTypDokumentu() {
        return "Protokol kontroli podatkowej";
    }
}
