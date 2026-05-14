package pl.kinga.oop.healthcare;

import java.util.Collections;
import java.util.List;

public class Prescription {
    private final String prescriptionId;
    private final Patient patient;
    private final Doctor doctor;
    private final List<Medicine> medicines;

    public Prescription(String prescriptionId, Patient patient, Doctor doctor, List<Medicine> medicines) {
        if (prescriptionId == null || prescriptionId.isEmpty() || patient == null || doctor == null || medicines == null || medicines.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.doctor = doctor;
        this.medicines = List.copyOf(medicines);
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<Medicine> getMedicines() {
        return Collections.unmodifiableList(medicines);
    }

    public int getMedicineCount() {
        return medicines.size();
    }

    public String getPatientName() {
        return patient.getFullName();
    }

    public String getDoctorTitle() {
        return doctor.getFullTitle();
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", patient=" + patient.getFullName() +
                ", doctor=" + doctor.getFullTitle() +
                ", medicines=" + getMedicineCount() +
                '}';
    }
}
