package pl.kinga.kolekcjegeneryki.publicsector.publicsectorgenerics;

public record Citizen(String id, String fullName, int birthYear) implements Identifiable {

    public Citizen {
        if (id == null || id.isEmpty() || fullName == null || fullName.isEmpty() || birthYear < 1900) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

    @Override
    public String getId() {
        return id;
    }
}
