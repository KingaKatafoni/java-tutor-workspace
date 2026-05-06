package pl.kinga.oop.sender;

public class Shipment implements Trackable, Auditable {

    @Override
    public String getStatus() {
        return "Status: " +  Trackable.super.getStatus().substring(8) + " | " + Auditable.super.getStatus().substring(8);
    }
}
