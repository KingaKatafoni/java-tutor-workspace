package pl.kinga.oop.logistic2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<String> parcels = new ArrayList<>();
        parcels.add("PKG-001");
        parcels.add("PKG-002");
        parcels.add("PKG-003");
        parcels.add("PKG-004");
        parcels.add("PKG-005");
        parcels.add("PKG-006");

        Waybill waybill = new Waybill("WB-2026-08841", "Kamil Dudko", "Marzena Kali", parcels);

        System.out.println(waybill);
        parcels.add("PKG-007");
        System.out.println(waybill);

        System.out.println("Waybill " + waybill.waybillNumber() + " has parcel: PKG-003 ? " +  waybill.hasParcel("PKG-003"));
        System.out.println("Number of parcels: " + waybill.getParcelCount());

        Waybill waybill2 = new Waybill("WB-2026-08856", "Sabina Macko", "Marzena Kali", parcels);
        Waybill waybill3 = new Waybill("WB-2026-08856", "Sabina Macko", "Marzena Kali", parcels);
        System.out.println("Are waybill2 equals waybill3 ? " + waybill2.equals(waybill3));
    }
}
