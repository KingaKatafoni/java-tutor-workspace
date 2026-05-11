package pl.kinga.oop.publicsector2;

public class Main {
    public static void main(String[] args) {
        OfficialAddress address = new OfficialAddress("Konin", "Petuniowa", "6", "62-510");

        System.out.println(address);
        address = new OfficialAddress("Poznan", "Kula", "12", "61-250");
        System.out.println(address);

    }
}
