package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_4;

public interface Policy {
    String getPolicyType();

    String getPolicyNumber();

    String getOwnerName();

    double calculatePremium();

    double getCoverage();
}
