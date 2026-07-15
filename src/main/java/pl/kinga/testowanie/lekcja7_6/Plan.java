package pl.kinga.testowanie.lekcja7_6;

public enum Plan {
    BASIC(29.99, 100, 3),
    STANDARD(49.99,  400, 10),
    PREMIUM(79.99, 800, 30),
    UNLIMITED(99.99, Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final double monthlyPrice;
    private final int includedMinutes;
    private final int includedGigabytes;

    Plan(double monthlyPrice, int includedMinutes, int includedGigabytes){
        this.monthlyPrice = monthlyPrice;
        this.includedMinutes = includedMinutes;
        this.includedGigabytes = includedGigabytes;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public int getIncludedMinutes() {
        return includedMinutes;
    }

    public int getIncludedGigabytes() {
        return includedGigabytes;
    }
}
