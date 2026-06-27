package pl.kinga.funkcyjnajava.lekcja5_13.healthcare;

public record PatientCard(String patientId, String fullName, String bloodType, Prescription currentPrescription, String allergies) {
}
