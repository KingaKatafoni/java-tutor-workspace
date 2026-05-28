package pl.kinga.kolekcjegeneryki.logistics.logisticsgeneric;

public record Letter(String id, String destination) {
    public Letter {
        if (id == null || id.isEmpty() || destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
