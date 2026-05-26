package pl.kinga.kolekcjegeneryki.logistics;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ShipmentTracker shipmentTracker = new ShipmentTracker();
        shipmentTracker.registerShipment("PKG-003");
        shipmentTracker.registerShipment("PKG-001");
        shipmentTracker.registerShipment("PKG-002");

        List<String> statuses1 = List.of("REGISTERED", "SORTED");
        List<String> statuses2 = List.of("REGISTERED", "SORTED", "IN_TRANSIT", "OUT_FOR_DELIVERY", "DELIVERED");
        List<String> statuses3 = List.of("REGISTERED", "SORTED", "IN_TRANSIT");

        for (Map.Entry<String, List<String>> entry : Map.of("PKG-001", statuses1, "PKG-002", statuses2, "PKG-003", statuses3).entrySet()) {
            for (String status : entry.getValue()) {
                shipmentTracker.addStatus(entry.getKey(), status);
            }
        }

        shipmentTracker.printAllShipments();
        shipmentTracker.addStatus("PKG-006", "LOST");

    }
}
