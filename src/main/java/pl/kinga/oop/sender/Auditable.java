package pl.kinga.oop.sender;

public interface Auditable {
    public default String getStatus() {
        return "Status: audit pending";
    }
}
