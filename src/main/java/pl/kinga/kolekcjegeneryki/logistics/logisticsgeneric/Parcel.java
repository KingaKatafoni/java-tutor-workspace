package pl.kinga.kolekcjegeneryki.logistics.logisticsgeneric;

public record Parcel(String id, double weight) {
    public Parcel {
        if (id == null || id.isEmpty() || weight < 0.0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
