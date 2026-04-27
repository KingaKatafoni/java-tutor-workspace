package pl.kinga.fundamenty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class KalkulatorPIT {
    public static final BigDecimal KWOTA_WOLNA = new BigDecimal("30000").setScale(2, RoundingMode.HALF_UP);
    public static final BigDecimal PROG_PIERWSZY = new BigDecimal("120000").setScale(2, RoundingMode.HALF_UP);
    public static final BigDecimal STAWKA_12 = new BigDecimal("0.12");
    public static final BigDecimal STAWKA_32 = new BigDecimal("0.32");
    public static final BigDecimal STAWKA_LINIOWA = new BigDecimal("0.19");

    public static void pobierzDane() {
        Scanner scanner = new Scanner(System.in);
        String imie;
        BigDecimal dochod;
        String formaOpodatkowania;
        String odpowiedz = "tak";
        int licznik = 0;

        while (odpowiedz.equals("tak")) {
            System.out.print("Imie i nazwisko ");
            imie = scanner.nextLine();
            System.out.print("Roczny dochód brutto (zl):  ");
            dochod = new BigDecimal(scanner.nextLine());
            System.out.print("Forma opodatkowania (skala/liniowy): ");
            formaOpodatkowania = scanner.nextLine();

            licznik++;
            wyswietlDeklaracje(imie, dochod, formaOpodatkowania);

            System.out.print("Kolejna deklaracja? (tak/nie):");
            odpowiedz = scanner.nextLine();
        }

        System.out.println("Obliczono " + licznik + " deklaracje. Do widzenia!");
        scanner.close();

    }

    public static BigDecimal[] obliczPodatekSkala(BigDecimal dochod) {
        //tablica podatek zawiera 2 wartosci dla progu I i progu II
        BigDecimal[] podatek = new BigDecimal[2];

        if (dochod.compareTo(KWOTA_WOLNA) <= 0) {
            podatek[0] = new BigDecimal("0");
            podatek[1] = new BigDecimal("0");
        } else if (dochod.compareTo(PROG_PIERWSZY) <= 0) {
            podatek[0] = (dochod.subtract(KWOTA_WOLNA)).multiply(STAWKA_12).setScale(2, RoundingMode.HALF_UP);
            podatek[1] = new BigDecimal("0");
        } else {
            podatek[0] = (PROG_PIERWSZY.subtract(KWOTA_WOLNA)).multiply(STAWKA_12).setScale(2, RoundingMode.HALF_UP);
            podatek[1] = ((dochod.subtract(PROG_PIERWSZY)).multiply(STAWKA_32)).setScale(2, RoundingMode.HALF_UP);
        }
        return podatek;
    }

    public static BigDecimal obliczPodatekLiniowy(BigDecimal dochod) {
        return dochod.multiply(STAWKA_LINIOWA).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal obliczPodatekMiesieczny(BigDecimal podatekRoczny) {
        return podatekRoczny.divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
    }

    public static void wyswietlDeklaracje(String imie, BigDecimal dochod, String forma) {
        StringBuilder kalkulatorPIT = new StringBuilder();
        BigDecimal podatekLiniowy;
        BigDecimal podatekMiesieczny;

        kalkulatorPIT.append("\n=== KALKULATOR PIT ===\n")
                .append("\nImie i nazwisko: ")
                .append(imie)
                .append("\nRoczny dochod brutto (zl): ")
                .append(dochod)
                .append("\nForma: ")
                .append(forma);

        StringBuilder deklaracjaPodatkowaPIT = new StringBuilder();
        StringBuilder obliczenia = new StringBuilder();

        if (forma.equals("liniowy")) {
            deklaracjaPodatkowaPIT.append("\n========================================")
                    .append("\n    DEKLARACJA PODATKOWA PIT")
                    .append("\n========================================")
                    .append("\nPodatnik: ")
                    .append(imie)
                    .append("\nDochod roczny: ")
                    .append(dochod)
                    .append("\nForma: ")
                    .append("podatek liniowy");

            podatekLiniowy = obliczPodatekLiniowy(dochod);
            podatekMiesieczny = obliczPodatekMiesieczny(podatekLiniowy);
            String stawkaLiniowa = STAWKA_LINIOWA.multiply(new BigDecimal("100")) + "%";

            obliczenia
                    .append("\n--- OBLICZENIA ---")
                    .append("\nStawka: ")
                    .append(stawkaLiniowa)
                    .append("\nPodatek: ")
                    .append(podatekLiniowy)
                    .append(" zl\n")
                    .append("\nPODATEK ROCZNY: ")
                    .append(podatekLiniowy)
                    .append(" zl")
                    .append("\nPODATEK MIESIECZNY: ")
                    .append(podatekMiesieczny)
                    .append(" zl")
                    .append("\n========================================");
        } else {
            deklaracjaPodatkowaPIT.append("\n========================================")
                    .append("\n    DEKLARACJA PODATKOWA PIT")
                    .append("\n========================================")
                    .append("\nPodatnik: ")
                    .append(imie)
                    .append("\nDochod roczny: ")
                    .append(dochod)
                    .append(" zl")
                    .append("\nForma: ")
                    .append("skala podatkowa");

            BigDecimal podatekRocznySkala;
            BigDecimal podstawaProgI;
            BigDecimal podatekProgI;
            BigDecimal podstawaProgII;
            BigDecimal podatekProgII;
            String stawkaI = STAWKA_12.multiply(new BigDecimal("100")) + "%";
            String stawkaII = STAWKA_32.multiply(new BigDecimal("100")) + "%";
            BigDecimal[] podatek = obliczPodatekSkala(dochod);

            if (dochod.compareTo(KWOTA_WOLNA) <= 0) {
                podstawaProgI = new BigDecimal("0");
                podatekProgI = new BigDecimal("0");
                podstawaProgII = new BigDecimal("0");
                podatekProgII = new BigDecimal("0");
                podatekRocznySkala = new BigDecimal("0");
                podatekMiesieczny = new BigDecimal("0");
            } else if (dochod.compareTo(PROG_PIERWSZY) <= 0) {
                podstawaProgI = dochod.subtract(KWOTA_WOLNA);
                podatekProgI = podatek[0];
                podstawaProgII = new BigDecimal("0");
                podatekProgII = new BigDecimal("0");
                podatekRocznySkala = (podatekProgI).add(podatek[1]);
                podatekMiesieczny = obliczPodatekMiesieczny(podatekRocznySkala);
            } else {
                podstawaProgI = PROG_PIERWSZY.subtract(KWOTA_WOLNA);
                podatekProgI = podatek[0];
                podstawaProgII = dochod.subtract(PROG_PIERWSZY);
                podatekProgII = podatek[1];
                podatekRocznySkala = podatekProgI.add(podatekProgII);
                podatekMiesieczny = obliczPodatekMiesieczny(podatekRocznySkala);
            }

            obliczenia
                    .append("\n--- OBLICZENIA ---")
                    .append("\nKwota wolna: ")
                    .append(KWOTA_WOLNA)
                    .append(" zl")
                    .append("\nPodstawa (I prog): ")
                    .append(podstawaProgI)
                    .append(" zl")
                    .append("\nPodatek I prog: ")
                    .append(podatekProgI)
                    .append(" zl (")
                    .append(stawkaI)
                    .append(")")
                    .append("\nPodstawa (II prog): ")
                    .append(podstawaProgII)
                    .append(" zl")
                    .append("\nPodatek II prog: ")
                    .append(podatekProgII)
                    .append(" zl (")
                    .append(stawkaII)
                    .append(")\n")
                    .append("\nPODATEK ROCZNY: ")
                    .append(podatekRocznySkala)
                    .append(" zl")
                    .append("\nPODATEK MIESIECZNY: ")
                    .append(podatekMiesieczny)
                    .append(" zl")
                    .append("\n========================================");
        }
        System.out.println(kalkulatorPIT);
        System.out.println(deklaracjaPodatkowaPIT);
        System.out.println(obliczenia);

    }

    public static void main(String[] args) {
        pobierzDane();
    }
}
