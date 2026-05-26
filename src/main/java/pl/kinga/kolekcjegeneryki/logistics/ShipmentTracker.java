package pl.kinga.kolekcjegeneryki.logistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShipmentTracker {
    private Map<String, List<String>> shipments;

    public ShipmentTracker() {
        this.shipments = new LinkedHashMap<>();
    }

    public void registerShipment(String trackingNumber) {
        List<String> elem = shipments.putIfAbsent(trackingNumber, new ArrayList<>());
        if (elem != null) {
            System.out.println("Shipment already exists in registry!");
        }
    }

    public void addStatus(String trackingNumber, String status) {
        List<String> statuses = shipments.get(trackingNumber);
        if (statuses == null) {
            System.out.println("The shipment doesn't exist!");
        } else {
            statuses.add(status);
        }
    }

    public List<String> getStatuses(String trackingNumber) {
        return shipments.get(trackingNumber);
    }

    public void printAllShipments() {
        System.out.println("----------Shipments---------");
        for (Map.Entry<String, List<String>> entry : shipments.entrySet()) {
            System.out.println("Shipment number: " + entry.getKey() + " -> Statuses: " + entry.getValue());
        }
    }
}
