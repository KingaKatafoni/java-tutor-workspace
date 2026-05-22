package pl.kinga.kolekcjegeneryki.publicsector;

public record CaseTicket(String ticketId, String citizenName, String description) {

    public CaseTicket {
        if (ticketId == null || ticketId.isEmpty() || citizenName == null || citizenName.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
