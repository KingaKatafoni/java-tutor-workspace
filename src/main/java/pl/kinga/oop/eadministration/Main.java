package pl.kinga.oop.eadministration;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Konin", "Kwiatowa", "5", "62-510");
        ContactInfo contactInfo = new ContactInfo("kropka.pop@gmail.com", "+48678908234");
        CitizenProfile citizenProfile = new CitizenProfile("98032498765", "Kinga", "Binga", address, contactInfo);

        System.out.println(citizenProfile.getFormattedAddress());
        System.out.println(citizenProfile);

        System.out.println(citizenProfile.relocate(new Address("Poznan", "Wagrowska", " 16B", "61-257")));
        System.out.println(citizenProfile);

        System.out.println(citizenProfile.getEmail());
    }
}
