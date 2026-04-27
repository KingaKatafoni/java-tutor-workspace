package pl.kinga.fundamenty;

public class KalkulatorSkladkiOC {
   static double skladkaBazowa = 1200.00;
    public static double obliczSkladke(int wiekKierowcy, int latPrawaJazdy, boolean bylWypadek){
        double skladka = 0;

        if (wiekKierowcy >= 18 && wiekKierowcy < 25) {
            skladka = skladkaBazowa * 1.5;
        } else if((wiekKierowcy >= 25) && (wiekKierowcy <= 60)){
            skladka = skladkaBazowa;
        } else if (wiekKierowcy > 60) {
            skladka = skladkaBazowa * 1.3;
        } else {
            throw new IllegalArgumentException("Nie jest pełnoletni");
        }

        if (latPrawaJazdy < 2) {
            skladka *= 1.4;
        }

        if (bylWypadek) {
            skladka *= 2;
        }

        return skladka;
    }

    static void main() {
        System.out.println(KalkulatorSkladkiOC.obliczSkladke(22,1, false));
        System.out.println(KalkulatorSkladkiOC.obliczSkladke(35,10, true));
        System.out.println(KalkulatorSkladkiOC.obliczSkladke(65,40, false));
    }
}
