package pl.kinga.oop.healthcare;

public record Patient(String pesel, String firstName, String lastName) {
    public Patient {
        if (pesel == null || pesel.length() != 11 || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Input value are incorrect");
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
