package pl.kinga.testowanie.lekcja7_5;

public class TariffCalculator {
    public double calculateMonthlyCost(Plan plan, int usedMinutes, int usedGigabytes) {
        if (plan == null) {
            throw new IllegalArgumentException("Plan cannot be null");
        }

        if (usedMinutes < 0 || usedGigabytes < 0) {
            throw new IllegalArgumentException("Usage cannot be negative");
        }

        double excessMinutes;
        double excessGigabytes;

        if (usedMinutes > plan.getIncludedMinutes()) {
            excessMinutes =  usedMinutes - plan.getIncludedMinutes();
        } else {
            excessMinutes = 0;
        }

        if (usedGigabytes > plan.getIncludedGigabytes()) {
            excessGigabytes = usedGigabytes - plan.getIncludedGigabytes();
        } else {
            excessGigabytes = 0;
        }

        double cost = plan.getMonthlyPrice() + (excessMinutes * 0.29) + (excessGigabytes * 15.00);


        return Math.round(cost * 100.0) / 100.0;
    }

    public String recommendPlan(int minutesNeeded, int gigabytesNeeded) {
        if (minutesNeeded < 0 || gigabytesNeeded < 0) {
            throw new IllegalArgumentException("Usage cannot be negative");
        }

        for (Plan plan : Plan.values()) {
            if (minutesNeeded <= plan.getIncludedMinutes() && gigabytesNeeded <= plan.getIncludedGigabytes()) {
                return plan.name();
            }
        }
        return Plan.UNLIMITED.name();
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }

        return phoneNumber.length() == 9 && phoneNumber.matches("\\d+") && (phoneNumber.startsWith("5") || phoneNumber.startsWith("6") || phoneNumber.startsWith("7") || phoneNumber.startsWith("8"));
    }
}
