package pl.kinga.kolekcjegeneryki.healthcare.healthcaredeque;

public record Patient(String ticketNumber, String name) {

    public Patient {
        if (ticketNumber == null || ticketNumber.isEmpty() || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
