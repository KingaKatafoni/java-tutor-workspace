package pl.kinga.oop.telecommunication;

public enum TicketPriority {
    CRITICAL("Awaria masowa, brak uslugi", 4),
    HIGH("Pojedynczy klient bez uslugi", 8),
    MEDIUM("Czesciowe ograniczenie uslugi", 24),
    LOW("Pytanie, prosba o informace", 72);

    private final String description;
    private final int slaHours;

    TicketPriority(String description, int slaHours) {
        this.description = description;
        this.slaHours = slaHours;
    }

    public String getDescription() {
        return description;
    }

    public int getSlaHours() {
        return slaHours;
    }

    public boolean isUrgent() {
        return slaHours <= 8;
    }

    public String getSlaDescription() {
        return "Reaction within " + slaHours + " hours";
    }

    @Override
    public String toString() {
        return name() +
                " (" + description + ", SLA: " +
                slaHours + "h)";
    }
}
