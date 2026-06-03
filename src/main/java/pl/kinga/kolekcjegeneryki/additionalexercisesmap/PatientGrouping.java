package pl.kinga.kolekcjegeneryki.additionalexercisesmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientGrouping {
    record Patient(String name, String department) {
    }

    public static void main(String[] args) {
        List<Patient> patients = List.of(
                new Patient("Kowalski", "Cardiology"),
                new Patient("Nowak", "Neurology"),
                new Patient("Wisniewska", "Cardiology"),
                new Patient("Zielinski", "Orthopedics"),
                new Patient("Lewandowska", "Neurology"),
                new Patient("Wojcik", "Cardiology"),
                new Patient("Kaminski", "Orthopedics"),
                new Patient("Szymanska", "Neurology")
        );

        Map<String, List<Patient>> groupedPatients = new HashMap<>();

        for (Patient p : patients) {
            String key = p.department();
            if (!groupedPatients.containsKey(key)) {
                groupedPatients.put(key, new ArrayList<>());
            }
            groupedPatients.get(key).add(p);
        }

        for (Map.Entry<String, List<Patient>> entry : groupedPatients.entrySet()) {
            System.out.println(entry.getKey());
            for (Patient patient : entry.getValue()) {
                System.out.println(" - " + patient.name());
            }
        }

        for (Map.Entry<String, List<Patient>> entry : groupedPatients.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().size());
        }
    }
}
