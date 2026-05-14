package pl.kinga.oop.healthcare;

public record Doctor(String licenseNumber, String firstName, String lastName, String specialization) {

    public Doctor {
        if (licenseNumber == null || licenseNumber.isEmpty() || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || specialization == null || specialization.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
    }

    public String getFullTitle() {
        return "Dr " + firstName + " " + lastName + " (" + specialization + ")";
    }

}
