package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

public class TravelPolicy implements Policy {
    private final String policyNumber;
    private final String ownerName;
    private final int durationDays;

    public TravelPolicy(String policyNumber, String ownerName, int durationDays) {
        if (durationDays <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        this.policyNumber = policyNumber;
        this.ownerName = ownerName;
        this.durationDays = durationDays;
    }

    @Override
    public String getPolicyType() {
        return "TRAVEL";
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
        return durationDays * 15.0;
    }

    @Override
    public double getCoverage() {
        return 10000.0;
    }
}
