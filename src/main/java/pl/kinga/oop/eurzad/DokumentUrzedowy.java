package pl.kinga.oop.eurzad;

import java.time.LocalDate;

public abstract class DokumentUrzedowy {
    private String sygnatura;
    private LocalDate dataUtworzenia;
    private String autor;

    public DokumentUrzedowy(String sygnatura, LocalDate dataUtworzenia, String autor) {
        this.sygnatura = sygnatura;
        this.dataUtworzenia = dataUtworzenia;
        this.autor = autor;
    }

    public String getSygnatura() {
        return sygnatura;
    }

    public LocalDate getDataUtworzenia() {
        return dataUtworzenia;
    }

    public String getAutor() {
        return autor;
    }

    public abstract String getTypDokumentu();

    public String getInfo(){
        return
                getSygnatura()
                + " | "
                + getTypDokumentu()
                + " | "
                + getDataUtworzenia()
                + " | "
                + getAutor();
    }
}
