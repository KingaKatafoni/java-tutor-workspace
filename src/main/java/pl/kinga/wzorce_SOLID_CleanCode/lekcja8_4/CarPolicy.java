package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

public class CarPolicy implements Policy {
    private final String policyNumber;
    private final String ownerName;
    private final int driverAge;

    public CarPolicy(String policyNumber, String ownerName, int driverAge) {
        if (driverAge < 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        this.policyNumber = policyNumber;
        this.ownerName = ownerName;
        this.driverAge = driverAge;
    }


    @Override
    public String getPolicyType() {
        return "CAR";
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
        if (driverAge < 25) {
            return 1800.0;
        } else if (driverAge > 65) {
            return 1500.0;
        } else {
            return 1200.0;
        }
    }

    @Override
    public double getCoverage() {
        return 50000.00;
    }
}
