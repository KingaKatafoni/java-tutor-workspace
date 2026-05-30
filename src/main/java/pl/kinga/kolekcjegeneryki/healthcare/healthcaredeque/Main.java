package pl.kinga.kolekcjegeneryki.healthcare.healthcaredeque;

public class Main {
    public static void main(String[] args) {
        PatientQueue patientQueue = new PatientQueue();

        Patient patient1 = new Patient("001", "Halina Klarna");
        Patient patient2 = new Patient("002", "Marek Mostowiak");
        Patient patient3 = new Patient("003", "Zygmunt Chajzer");
        Patient patient4 = new Patient("004", "Anna Grabarczyk");
        Patient patient5 = new Patient("005", "Marcin Mroczek");

        patientQueue.registerPatient(patient1);
        patientQueue.registerPatient(patient2);
        patientQueue.registerPatient(patient3);
        patientQueue.registerPatient(patient4);
        patientQueue.registerPatient(patient5);

        System.out.println("-------Healthcare Queue-----");
        patientQueue.printQueue();
        System.out.println("Next patient: " + patientQueue.peekNextPatient());
        System.out.println("------Patients Queue after nextPatient()-----");
        patientQueue.callNextPatient();
        patientQueue.callNextPatient();
        patientQueue.printQueue();

        patientQueue.callNextPatient();
        patientQueue.callNextPatient();
        patientQueue.callNextPatient();


    }
}
