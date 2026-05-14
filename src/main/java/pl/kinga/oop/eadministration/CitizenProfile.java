package pl.kinga.oop.eadministration;

public class CitizenProfile {
    private String pesel;
    private String firstName;
    private String lastName;
    private Address address;
    private ContactInfo contactInfo;

    public CitizenProfile(String pesel, String firstName, String lastName, Address address, ContactInfo contactInfo) {
        if (pesel == null || pesel.length() != 11 || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || address == null || contactInfo == null) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public String getFormattedAddress() {
        return address.formatShort();
    }

    public String getEmail() {
        return contactInfo.email();
    }

    public Address relocate(Address newAddress) {
        return this.address = newAddress;

    }

    @Override
    public String toString() {
        return "CitizenProfile{" +
                "pesel='" + pesel + '\'' +
                ", name='" + firstName + " " + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
