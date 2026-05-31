package pl.kinga.kolekcjegeneryki.logistics.logisticsfail;

public class Main {
    public static void main(String[] args) {
        ShipmentCleaner shipmentCleaner = new ShipmentCleaner();

        shipmentCleaner.addShipment(new Shipment("POL-57488-001", "REGISTERED", 26));
        shipmentCleaner.addShipment(new Shipment("POL-57432-002", "IN_TRANSIT", 1));
        shipmentCleaner.addShipment(new Shipment("EN-57095-003", "REGISTERED", 6));
        shipmentCleaner.addShipment(new Shipment("ES-57467-004", "DELIVERED", 30));
        shipmentCleaner.addShipment(new Shipment("NOR-57567-005", "REGISTERED", 22));
        shipmentCleaner.addShipment(new Shipment("POL-57023-006", "IN_TRANSIT", 15));
        shipmentCleaner.addShipment(new Shipment("SWE-57035-007", "DELIVERED", 0));

        shipmentCleaner.printAll();
        System.out.println("-----remove delivered-----");
        shipmentCleaner.removeDelivered();
        shipmentCleaner.printAll();
        System.out.println("-------remove older than---------");
        shipmentCleaner.removeOlderThan(7);
        shipmentCleaner.printAll();
    }
}
