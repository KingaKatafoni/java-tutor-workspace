package pl.kinga.kolekcjegeneryki.insurance.insurancepecs;

public class LifePolicy extends Policy {
    private int coverageYears;

    public LifePolicy(String policyNumber, double premium, int coverageYears) {
        super(policyNumber, premium);
        if (coverageYears < 0 ){
            throw new IllegalArgumentException("Input value is incorrect!");
        }
        this.coverageYears = coverageYears;
    }

    public int getCoverageYears() {
        return coverageYears;
    }

    @Override
    public String toString() {
        return "LifePolicy{" + super.toString() +
                "coverageYears=" + coverageYears +
                '}';
    }
}
