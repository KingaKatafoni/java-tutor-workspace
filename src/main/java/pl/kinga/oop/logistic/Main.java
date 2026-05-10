package pl.kinga.oop.logistic;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Parcel parcelOne = new Parcel("PKG-90210", 23.0, "Krakow");
        Parcel parcelTwo = new Parcel("PKG-50670", 13.0, "Poznan");
        Parcel parcelThree = new Parcel("PKG-60890", 45.0, "Konin");

        parcelOne.markAsDelivered();
        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels.add(parcelOne);
        parcels.add(parcelTwo);
        parcels.add(parcelThree);

        System.out.println(parcels);
        System.out.println("Status: " + parcelOne);
    }
}
