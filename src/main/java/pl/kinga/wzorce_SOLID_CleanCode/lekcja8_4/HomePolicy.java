package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

public class HomePolicy implements Policy {
    private final String policyNumber;
    private final String ownerName;
    private final int areaM2;

    public HomePolicy(String policyNumber, String ownerName, int areaM2) {
        if (areaM2 <= 0) {
            throw new IllegalArgumentException("Area must be positive");
        }
        this.policyNumber = policyNumber;
        this.ownerName = ownerName;
        this.areaM2 = areaM2;
    }


    @Override
    public String getPolicyType() {
        return "HOME";
    }

    @Override
    public String getPolicyNumber() {
        return policyNumber;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public double calculatePremium() {
        return areaM2 * 5.0;
    }

    @Override
    public double getCoverage() {
        return 300000.0;
    }
}
