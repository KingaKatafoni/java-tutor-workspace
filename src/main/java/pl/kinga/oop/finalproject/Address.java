package pl.kinga.oop.finalproject;

public record Address(String city, String street, String buildingNumber, String postalCode) {
    public Address {
        if (city == null || city.isEmpty() || street == null || street.isEmpty() || buildingNumber == null || buildingNumber.isEmpty() || postalCode == null || postalCode.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
    }

    public String formatShort() {
        return street + " " +
                buildingNumber + ", " +
                postalCode + " " +
                city;
    }
}
