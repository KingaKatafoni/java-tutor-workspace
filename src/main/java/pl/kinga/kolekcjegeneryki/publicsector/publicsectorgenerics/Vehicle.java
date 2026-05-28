package pl.kinga.kolekcjegeneryki.publicsector.publicsectorgenerics;

public record Vehicle(String id, String brand, int productionYear) implements Identifiable {

    public Vehicle {
        if (id == null || id.isEmpty() || brand == null || brand.isEmpty() || productionYear < 1886) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

    @Override
    public String getId() {
        return id;
    }
}
