package pl.kinga.oop.finalproject;

public enum ResidentStatus {
    ACTIVE("Active resident"),
    TEMPORARILY_ABSENT("Temporarily abroad"),
    EMIGRATED("Permanently emigrated");

    private final String description;

    ResidentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return this == ResidentStatus.ACTIVE;
    }
}

