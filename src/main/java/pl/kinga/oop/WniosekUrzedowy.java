package pl.kinga.oop;

public class WniosekUrzedowy {
    String numerWniosku;
    String imieWnioskodawcy;
    String nazwiskoWnioskodawcy;
    String typWniosku;
    String dataZlozenia;
    String status;

    void wyswietlPodsumowanie(){
        StringBuilder podsumowanie = new StringBuilder();
        podsumowanie.append("--- Wniosek " )
                .append(this.numerWniosku)
                .append(" ---")
                .append("\nWnioskodawca: ")
                .append(this.imieWnioskodawcy)
                .append(" ")
                .append(this.nazwiskoWnioskodawcy)
                .append("\nTyp: ")
                .append(this.typWniosku)
                .append("\nData: ")
                .append(this.dataZlozenia)
                .append("\nStatus: ")
                .append(this.status)
                .append("\n");

        System.out.println(podsumowanie);
    }

    void zmienStatus(String nowyStatus){
        StringBuilder nowePodsumowanie = new StringBuilder();
        this.status = nowyStatus;
        nowePodsumowanie.append("Status wniosku ")
                .append(this.numerWniosku)
                .append(" zmieniony na: ")
                .append(nowyStatus)
                .append("\n");
        System.out.println(nowePodsumowanie);
    }

}
