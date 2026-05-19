package pl.kinga.oop.finalproject;

public class ForeignResident extends Resident {
    private final String nationality;
    private final String permitNumber;

    public ForeignResident(String pesel, String firstName, String lastName, Address address, String nationality, String permitNumber) {
        super(pesel, firstName, lastName, address);

        if (nationality == null || nationality.isEmpty() || permitNumber == null || permitNumber.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.nationality = nationality;
        this.permitNumber = permitNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    @Override
    public String getResidentType() {
        return "Foreign Resident";
    }

    @Override
    public boolean matchQuery(String query) {
        return getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                getLastName().toLowerCase().contains(query.toLowerCase()) ||
                getPesel().toLowerCase().contains(query.toLowerCase()) ||
                nationality.toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "nationality=" + nationality +
                ", permitNumber=" + permitNumber;
    }
}
