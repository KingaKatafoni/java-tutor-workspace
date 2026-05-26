package pl.kinga.kolekcjegeneryki.insurance;

public record PolicyInfo(String holderName, String type, double premium) {

    public PolicyInfo {
        if (holderName == null || holderName.isEmpty() || type == null || type.isEmpty() || premium < 0) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }
}
