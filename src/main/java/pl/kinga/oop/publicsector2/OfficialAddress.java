package pl.kinga.oop.publicsector2;

public final class OfficialAddress {
    private final String city;
    private final String street;
    private final String buildingNumber;
    private final String postalCode;

    public OfficialAddress(String city, String street, String buildingNumber, String postalCode) {
        if (city == null || city.isEmpty() || street == null || street.isEmpty() || buildingNumber == null || buildingNumber.isEmpty() || postalCode == null || postalCode.isEmpty()) {
            throw new IllegalArgumentException("No null and empty parameters allowed!");
        }
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "OfficialAddress{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
