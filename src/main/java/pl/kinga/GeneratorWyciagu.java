package pl.kinga;

public class GeneratorWyciagu {
    public static String generujWyciag(String numerKonta, String[] opisy, double[] kwoty){
        StringBuilder wyciag = new StringBuilder();
        double saldo = 0;
        wyciag.append("========= WYCIAG BANKOWY ========\n")
                .append("Konto: " )
                .append(numerKonta)
                .append("\n---------------------------\n");

            for (int i = 0; i < opisy.length; i++) {
                wyciag
                        .append(i +1)
                        .append(" ")
                        .append(opisy[i])
                        .append(" ")
                        .append(kwoty[i])
                        .append("\n");

                saldo += kwoty[i];
            }
            wyciag
                    .append("---------------------------\n")
                    .append("SALDO: ")
                    .append(saldo)
                    .append(" PLN \n")
                    .append("================================");
        return wyciag.toString();
    }

    public static void main(String[] args) {
        String[] opisy = {"Przelew ZUS", "Wyplata", "Oplata za telefon"};
        double[] kwoty = {-1500.00, 4200.00, -49.99};

        String wyciag = generujWyciag("PL61 1090 1014 0000 0712 1981 2874", opisy, kwoty);
        System.out.println(wyciag);
    }
}
