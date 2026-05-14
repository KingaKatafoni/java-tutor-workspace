package pl.kinga.oop.healthcare;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Patient patient1 = new Patient("94062598765", "Piotr", "Skonieczny");
        Doctor doctor1 = new Doctor("PWZ-1234567", "Janusz", "Doktor", "Cardiology");
        System.out.println(doctor1.getFullTitle());
        System.out.println(patient1.getFullName());
        Medicine medicineAspirin = new Medicine("Aspirin", "100", 3);
        Medicine medicinePainKiller = new Medicine("Paracetamol", "200", 4);
        Medicine medicinePenicilin = new Medicine("Penicilin", "500", 2);

        List<Medicine> medicines = new ArrayList<>();
        medicines.add(medicineAspirin);
        medicines.add(medicinePainKiller);
        medicines.add(medicinePenicilin);

        Prescription prescription1 = new Prescription("RX-2026-001", patient1, doctor1, List.of(medicineAspirin, medicinePainKiller, medicinePenicilin));

        System.out.println(prescription1.getDoctorTitle());
        System.out.println(prescription1.getPatientName());
        System.out.println(prescription1);

        for (Medicine medicine : prescription1.getMedicines()) {
            System.out.println(medicine);
        }
    }
}