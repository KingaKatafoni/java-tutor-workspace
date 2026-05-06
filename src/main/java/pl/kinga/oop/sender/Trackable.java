package pl.kinga.oop.sender;

public interface Trackable {
    public default String getStatus() {
        return "Status: in transit";
    }
}
