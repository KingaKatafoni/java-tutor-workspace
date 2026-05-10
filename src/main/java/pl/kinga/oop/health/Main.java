package pl.kinga.oop.health;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PatientRecord patientOne = new PatientRecord("98122498077", "Krystula Pimpunla", "Asthma");
        PatientRecord patientTwo = new PatientRecord("93051256087", "Karolina Kamien", "Depression");
        PatientRecord patientThree = new PatientRecord("90121264809", "Anastazja Wager", "Fever");

        System.out.println(patientOne);
        System.out.println(patientTwo);
        System.out.println(patientThree);

        ArrayList<PatientRecord> patientRecords = new ArrayList<>();
        patientRecords.add(patientOne);
        patientRecords.add(patientTwo);
        patientRecords.add(patientThree);

        System.out.println("------------------------");
        System.out.println(patientRecords);

        PatientRecord patientFour = new PatientRecord("98122498077", "Adrian Nowaczyk", "Demetia");
        System.out.println("Znaleziono: " + patientFour);
    }
}
