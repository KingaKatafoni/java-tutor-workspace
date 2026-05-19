package pl.kinga.oop.finalproject;

public class Citizen extends Resident {
    private final ResidentStatus status;

    public Citizen(String pesel, String firstName, String lastName, Address address, ResidentStatus status) {
        super(pesel, firstName, lastName, address);

        if (status == null) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.status = status;
    }

    public ResidentStatus getStatus() {
        return status;
    }

    @Override
    public String getResidentType() {
        return "Citizen";
    }

    @Override
    public boolean matchQuery(String query) {
        return (getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                getLastName().toLowerCase().contains(query.toLowerCase()) ||
                getPesel().toLowerCase().contains(query.toLowerCase()));
    }

    @Override
    public String toString() {
        return super.toString() +
                ", {status=" + getStatus() + "}";
    }
}
