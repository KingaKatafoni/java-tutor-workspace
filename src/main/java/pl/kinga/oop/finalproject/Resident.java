package pl.kinga.oop.finalproject;

import java.util.Objects;

public abstract class Resident implements Searchable {
    private final String pesel;
    private final String firstName;
    private final String lastName;
    private final Address address;

    public Resident(String pesel, String firstName, String lastName, Address address) {
        if (pesel == null || pesel.length() != 11 || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || address == null) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getFormattedAddress() {
        return address.formatShort();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract String getResidentType();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Resident other = (Resident) obj;
        return pesel.equals(other.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    @Override
    public String toString() {
        String maskedPesel = pesel.substring(0, 3) + "********";
        return getResidentType() + "{" +
                "pesel='" + maskedPesel + '\'' +
                ", name='" + getFullName() + '\'' +
                ", address=" + address.formatShort() +
                "},  ";
    }

}
