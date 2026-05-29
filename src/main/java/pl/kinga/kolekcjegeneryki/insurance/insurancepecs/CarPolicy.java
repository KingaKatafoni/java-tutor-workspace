package pl.kinga.kolekcjegeneryki.insurance.insurancepecs;

public class CarPolicy extends Policy {
    private String vehicleBrand;

    public CarPolicy(String policyNumber, double premium, String vehicleBrand) {
        super(policyNumber, premium);
        if (vehicleBrand == null || vehicleBrand.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    @Override
    public String toString() {
        return "CarPolicy{" + super.toString() +
                "vehicleBrand='" + vehicleBrand + '\'' +
                '}';
    }
}
