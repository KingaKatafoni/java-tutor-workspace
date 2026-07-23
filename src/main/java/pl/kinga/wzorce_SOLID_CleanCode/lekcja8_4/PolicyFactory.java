package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

public class PolicyFactory {
    public static Policy create(String type, String policyNumber, String ownerName, int param) {
        return switch (type) {
            case "CAR" -> new CarPolicy(policyNumber, ownerName, param);
            case "HOME" -> new HomePolicy(policyNumber, ownerName, param);
            case "TRAVEL" -> new TravelPolicy(policyNumber, ownerName, param);
            default -> throw new IllegalArgumentException("Unknown policy type: " + type);
        };
    }
}
