package pl.kinga.kolekcjegeneryki.logistics.logisticsfail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShipmentCleaner {
    private List<Shipment> shipments;

    public ShipmentCleaner() {
        this.shipments = new ArrayList<>();
    }

    public void addShipment(Shipment s) {
        shipments.add(s);
    }

    public void removeDelivered() {
        shipments.removeIf(s -> s.status().contains("DELIVERED"));
    }

    public void removeOlderThan(int maxDays) {
        Iterator<Shipment> it = shipments.iterator();
        while (it.hasNext()) {
            if (it.next().daysInTransit() > maxDays) {
                it.remove();
            }
        }
    }

    public List<Shipment> getShipmentsReadOnly() {
        return Collections.unmodifiableList(shipments);
    }

    public void printAll() {
        for (Shipment shipment : shipments) {
            System.out.println(shipment);
        }
    }
}
