package pl.kinga.kolekcjegeneryki.healthcare;

public class Main {
    public static void main(String[] args) {
        Patient patientOne = new Patient("97865432189", "Krystyna Puls");
        Patient patientTwo = new Patient("64735524311", "Milosz Klosz");
        Patient patientThree = new Patient("97865432189", "Krystyna Puls");
        Patient patientFour = new Patient("98765432123", "Monika Polika");

        ClinicManager clinicManager = new ClinicManager();

        clinicManager.registerPatient(patientOne);
        clinicManager.registerPatient(patientTwo);
        clinicManager.registerPatient(patientThree);
        clinicManager.registerPatient(patientFour);

        clinicManager.addToWaitingRoom(patientTwo);
        clinicManager.addToWaitingRoom(patientFour);

        System.out.println(clinicManager.getWaitingCount());
        System.out.println(clinicManager.callNextPatient());

        System.out.println(clinicManager.callNextPatient());
        System.out.println(clinicManager.callNextPatient());
        System.out.println(clinicManager.getVisitCount("97865432189"));
        clinicManager.addToWaitingRoom(patientOne);
        clinicManager.addToWaitingRoom(patientOne);
        clinicManager.addToWaitingRoom(patientOne);
        clinicManager.callNextPatient();
        clinicManager.callNextPatient();

        System.out.println(clinicManager.getVisitCount("97865432189"));
        clinicManager.printVisitSummary();

    }
}
