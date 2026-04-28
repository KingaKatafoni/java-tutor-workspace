package pl.kinga.oop;

public class Main {
    public static void main(String[] args) {
        WniosekUrzedowy wniosekAK = new WniosekUrzedowy();
        wniosekAK.numerWniosku = "WN-2026/001";
        wniosekAK.imieWnioskodawcy = "Anna";
        wniosekAK.nazwiskoWnioskodawcy = "Kowalska";
        wniosekAK.typWniosku = "dowod osobisty";
        wniosekAK.dataZlozenia = "2026-04-27";
        wniosekAK.status = "zlozony";

        WniosekUrzedowy wniosekJN = new WniosekUrzedowy();
        wniosekJN.numerWniosku = "WN-2026/002";
        wniosekJN.imieWnioskodawcy = "Jan";
        wniosekJN.nazwiskoWnioskodawcy = "Nowak";
        wniosekJN.typWniosku = "meldunek";
        wniosekJN.dataZlozenia = "2026-04-27";
        wniosekJN.status = "zlozony";

        WniosekUrzedowy wniosekMW = new WniosekUrzedowy();
        wniosekMW.numerWniosku = "WN-2026/003";
        wniosekMW.imieWnioskodawcy = "Maria";
        wniosekMW.nazwiskoWnioskodawcy = "Wisniewska";
        wniosekMW.typWniosku = "odpis aktu urodzenia";
        wniosekMW.dataZlozenia = "2026-04-27";
        wniosekMW.status = "zlozony";

        wniosekAK.wyswietlPodsumowanie();
        wniosekJN.wyswietlPodsumowanie();
        wniosekMW.wyswietlPodsumowanie();

        wniosekAK.zmienStatus("zatwierdzony");
        wniosekJN.zmienStatus("w trakcie");

        wniosekAK.wyswietlPodsumowanie();
        wniosekJN.wyswietlPodsumowanie();



    }

}
