package pl.kinga.oop.health;

public class PatientRecord {
    private String pesel;
    private String fullName;
    private String diagnosis;

    public PatientRecord(String pesel, String fullName, String diagnosis) {
        this.pesel = pesel;
        this.fullName = fullName;
        this.diagnosis = diagnosis;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        String maskedPesel = pesel.substring(0, 3) + "********";
        return "PatientRecord{" +
                "pesel='" + maskedPesel + '\'' +
                ", fullName='" + fullName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
