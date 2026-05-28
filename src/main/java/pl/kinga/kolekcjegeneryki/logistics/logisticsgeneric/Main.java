package pl.kinga.kolekcjegeneryki.logistics.logisticsgeneric;

public class Main {
    public static void main(String[] args) {
        Warehouse<Parcel> parcelWarehouse = new Warehouse<>();
        parcelWarehouse.addItem(new Parcel("PAR-908-001", 35.00));
        parcelWarehouse.addItem(new Parcel("PAR-906-002", 115.00));
        parcelWarehouse.addItem(new Parcel("PAR-904-003", 6.00));
        parcelWarehouse.printAll();
        System.out.println("------------");

        Warehouse<Letter> letterWarehouse = new Warehouse<>();
        letterWarehouse.addItem(new Letter("LET-654-001", "Krakow"));
        letterWarehouse.addItem(new Letter("LET-654-002", "Berlin"));
        //parcelWarehouse.addItem(new Letter("PAR-990-004", "London")); // compile error — type safety!`

    }
}
