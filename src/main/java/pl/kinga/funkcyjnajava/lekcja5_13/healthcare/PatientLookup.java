package pl.kinga.funkcyjnajava.lekcja5_13.healthcare;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PatientLookup {
    public static void main(String[] args) {
        List<PatientCard> patients = Arrays.asList(
                new PatientCard("PAT001", "Anna Kowalska", "A+",
                        new Prescription("Metformin", "500mg 2x dziennie", LocalDate.of(2026, 12, 31)),
                        "penicillin"),
                new PatientCard("PAT002", "Jan Nowak", null,
                        new Prescription("Ibuprofen", "200mg w razie bolu", LocalDate.of(2026, 3, 15)),
                        null),
                new PatientCard("PAT003", "Maria Wiszniewska", "B-",
                        null, "sulfonamides, aspirin"),
                new PatientCard("PAT004", "Piotr Zielinski", "0+",
                        new Prescription("Atorvastatin", "20mg 1x dziennie", LocalDate.of(2027, 6, 1)),
                        null),
                new PatientCard("PAT005", "Ewa Dabrowska", null,
                        null, null)
        );

        System.out.println("---- 1# Medication of patient PAT001 ----");
        String medicationPatientPAT001 = patients.stream()
                .filter(p -> p.patientId().equals("PAT001"))
                .findFirst()
                .map(PatientCard::currentPrescription)
                .map(Prescription::medicationName)
                .orElse("Brak recepty");

        System.out.println(medicationPatientPAT001);

        System.out.println("---- 2# Medication of patient PAT003 ----");
        String medicationPatientPAT003 = patients.stream()
                .filter(p -> p.patientId().equals("PAT003"))
                .findFirst()
                .map(PatientCard::currentPrescription)
                .map(Prescription::medicationName)
                .orElse("Brak recepty");

        System.out.println(medicationPatientPAT003);

        System.out.println("---- 3# Medication dosage of patient PAT002 toUpperCase ----");
        String dosagePAT002 = patients.stream()
                .filter(p -> p.patientId().equals("PAT002"))
                .findFirst()
                .map(PatientCard::currentPrescription)
                .map(Prescription::dosage)
                .map(String::toUpperCase)
                .orElse("BRAK");

        System.out.println(dosagePAT002);

        System.out.println("---- 4# Blood type of patient PAT002 ----");
        String bloodTypePAT002 = patients.stream()
                .filter(p -> p.patientId().equals("PAT002"))
                .findFirst()
                .map(PatientCard::bloodType)
                .orElse("Nieznana");
        System.out.println(bloodTypePAT002);

        System.out.println("---- 5# Allergies of patient PAT001 if contain penicillin ----");
        String penicillinAllergiesPAT001 = patients.stream()
                .filter(p -> p.patientId().equals("PAT001"))
                .findFirst()
                .map(PatientCard::allergies)
                .filter(a -> a.contains("penicillin"))
                .orElse("Brak alergii na penicyline");
        System.out.println(penicillinAllergiesPAT001);

        System.out.println("---- 6# Allergies of patient PAT004 if contain penicillin ----");
        String penicillinAllergiesPAT004 = patients.stream()
                .filter(p -> p.patientId().equals("PAT004"))
                .findFirst()
                .map(PatientCard::allergies)
                .filter(a -> a.contains("penicillin"))
                .orElse("Brak alergii na penicyline");
        System.out.println(penicillinAllergiesPAT004);

        System.out.println("---- 7# Is prescription of patient PAT002 valid? ----");
        String validPrescrPAT002 = patients.stream()
                .filter(p -> p.patientId().equals("PAT002"))
                .findFirst()
                .map(PatientCard::currentPrescription)
                .map(Prescription::validUntil)
                .filter(d -> ((LocalDate) d).isAfter(LocalDate.now()))
                .map(d -> "Ważna do: " + d)
                .orElse("Recepta wygasla lub brak");
        System.out.println(validPrescrPAT002);

        System.out.println("---- 8# Is prescription of patient PAT004 valid? ----");
        String validPrescrPAT004 = patients.stream()
                .filter(p -> p.patientId().equals("PAT004"))
                .findFirst()
                .map(PatientCard::currentPrescription)
                .map(Prescription::validUntil)
                .filter(d -> ((LocalDate) d).isAfter(LocalDate.now()))
                .map(d -> "Ważna do: " + d)
                .orElse("Recepta wygasla lub brak");
        System.out.println(validPrescrPAT004);


    }
}
