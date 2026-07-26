package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_9;

public class ApplicationProcessor {

    private final ApplicationType applicationType;
    private final Citizen citizen;
    private final boolean urgent;
    private static final double ADDITIONAL_URGENT_FEE = 20.0;
    private static final int REQUIRED_PESEL_LENGTH = 11;
    private static final int REQUIRED_ADULT_AGE = 18;


    public ApplicationProcessor(ApplicationType applicationType, Citizen citizen, boolean urgent) {
        this.applicationType = applicationType;
        this.citizen = citizen;
        this.urgent = urgent;
    }

    public String processApplication() {
        if (urgent) {
            return processUrgentApplication();
        } else {
            return processStandardApplication();
        }
    }

    private String processUrgentApplication() {
        validateCitizenData(citizen);
        double fee = calculateFee(applicationType) + ADDITIONAL_URGENT_FEE;
        return buildUrgentConfirmation(fee);
    }

    private String processStandardApplication() {
        validateCitizenData(citizen);
        double fee = calculateFee(applicationType);
        return buildStandardConfirmation(fee);
    }


    private void validateCitizenData(Citizen citizen) {

        if (citizen == null) {
            throw new IllegalStateException("Citizen does not exist");
        }

        if (citizen.name() == null || citizen.name().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (citizen.pesel() == null || citizen.pesel().length() != REQUIRED_PESEL_LENGTH) {
            throw new IllegalArgumentException("PESEL must have 11 digits");
        }

        if (citizen.age() < REQUIRED_ADULT_AGE) {
            throw new IllegalArgumentException("Citizen must be adult");
        }
    }


    private double calculateFee(ApplicationType applicationType) {
        return switch (applicationType) {
            case RESIDENCE_REGISTRATION, RESIDENCE_DEREGISTRATION -> 0.0;
            case CERTIFICATE -> 17.0;
            case CERTIFIED_COPY -> 22.0;
            case OTHER -> 10.0;
        };
    }

    private String buildStandardConfirmation(double fee) {
        return "Wniosek: " + applicationType + " | Wnioskodawca: " + citizen.name() + " (PESEL: " + citizen.pesel() + ") | Wiek: " + citizen.age() + " | Oplata: " + fee + " PLN";
    }

    private String buildUrgentConfirmation(double fee) {
        return buildStandardConfirmation(fee) + " [PILNY]";
    }
}

