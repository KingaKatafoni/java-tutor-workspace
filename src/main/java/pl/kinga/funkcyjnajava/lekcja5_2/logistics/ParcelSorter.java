package pl.kinga.funkcyjnajava.lekcja5_2.logistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ParcelSorter {
    public static List<Parcel> sortParcels(List<Parcel> parcels, Comparator<Parcel> comparator){
        List<Parcel> copy = new ArrayList<>(parcels);
        Collections.sort(copy, comparator);
        return copy;
    }

    public static void groupAndPrint(List<Parcel> parcels, Function<Parcel, String> groupBy, Consumer<String> printer){
        for (Parcel parcel : parcels){
            String key = groupBy.apply(parcel);
            printer.accept(key);
        }
    }

    public static void main(String[] args){
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel("PAR/001", "Poznan", "Berlin", 23.9, "EXPRESS"));
        parcels.add(new Parcel("PAR/002", "Gdansk", "Warszawa", 123.9, "EXPRESS"));
        parcels.add(new Parcel("PAR/003", "Konin", "Olsztyn", 3.9, "STANDARD"));
        parcels.add(new Parcel("PAR/004", "Krakow", "Poznan", 44.0, "ECONOMY"));
        parcels.add(new Parcel("PAR/005", "Poznan", "Konin", 13.4, "ECONOMY"));
        parcels.add(new Parcel("PAR/006", "Warszawa", "Olsztyn", 3.4, "EXPRESS"));
        parcels.add(new Parcel("PAR/007", "Poznan", "Gdansk", 22.3, "STANDARD"));

        System.out.println("------sort by weight------");
        sortParcels(parcels, (a,b) -> Double.compare(a.weight(), b.weight())).forEach(System.out::println);
        System.out.println("------sort by priority------");
        sortParcels(parcels, Comparator.comparing(Parcel::priority)).forEach(System.out::println);
        System.out.println("------sort by weight descending------");
        sortParcels(parcels, Comparator.comparing(Parcel::weight).reversed()).forEach(System.out::println);
        System.out.println("------group city------");
        groupAndPrint(parcels, Parcel::receiverCity, System.out::println);
        System.out.println("------group description------");
        groupAndPrint(parcels, p -> p.trackingId() + " → " + p.receiverCity(), System.out::println);
    }

}
