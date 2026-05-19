package pl.kinga.oop.finalproject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        RegistrationOffice registrationOffice = new RegistrationOffice("Urzad Miasta Konin");

        Citizen citizenOne = new Citizen("93122409876", "Katarzyna", "Dowbor", new Address("Konin", "Kwiatowa", "5", "62-510"), ResidentStatus.ACTIVE);
        Citizen citizenTwo = new Citizen("85031265432", "Dominik", "Nobel", new Address("Poznan", "Mickiewicza", "12/9", "63-678"), ResidentStatus.EMIGRATED);
        Citizen citizenThree = new Citizen("93082609567", "Filip", "Filipowicz", new Address("Wrzesnia", "Zabytkowa", "162", "65-300"), ResidentStatus.TEMPORARILY_ABSENT);

        ForeignResident foreignResidentOne = new ForeignResident("12345678912", "Abdul", "Kabul", new Address("Viena", "Banhoff Strasse", "4", "12-345"), "UAE", "789-345-908");
        ForeignResident foreignResidentTwo = new ForeignResident("09876543219", "Kali", "Bali", new Address("Cairo", "Fruit", "43", "22-333"), "Egiptian", "290-654-098");

        registrationOffice.registerResident(citizenOne);
        registrationOffice.registerResident(citizenTwo);
        registrationOffice.registerResident(citizenThree);
        registrationOffice.registerResident(foreignResidentOne);
        registrationOffice.registerResident(foreignResidentTwo);

        registrationOffice.registerResident(citizenThree);

        System.out.println(registrationOffice.findByPesel("93122409876"));
        System.out.println(registrationOffice.findByPesel("90654321678"));

        System.out.println(registrationOffice.findByQuery("Kowalski"));

        for (Resident resident : registrationOffice.getAllResidents()) {
            System.out.println(resident.toString());
        }

        System.out.println(registrationOffice);


    }
}
