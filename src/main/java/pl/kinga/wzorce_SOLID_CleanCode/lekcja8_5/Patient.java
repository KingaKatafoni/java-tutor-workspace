package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_5;

public class Patient {
    private final String firstName;
    private final String lastName;
    private final String pesel;
    private final String phoneNumber;
    private final String email;
    private final String city;
    private final String bloodType;
    private final String allergies;
    private final String emergencyContact;

    private Patient(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.pesel = builder.pesel;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.city = builder.city;
        this.bloodType = builder.bloodType;
        this.allergies = builder.allergies;
        this.emergencyContact = builder.emergencyContact;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public static class Builder{
        private final String firstName;
        private final String lastName;
        private final String pesel;
        private String phoneNumber = null;
        private String email = null;
        private String city = null;
        private String bloodType = null;
        private String allergies = null;
        private String emergencyContact = null;

        public Builder(String firstName, String lastName, String pesel){
            this.firstName = firstName;
            this.lastName = lastName;
            this.pesel = pesel;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder city(String city){
            this.city = city;
            return this;
        }

        public Builder bloodType(String bloodType){
            this.bloodType = bloodType;
            return this;
        }

        public Builder allergies(String allergies){
            this.allergies = allergies;
            return this;
        }

        public Builder emergencyContact(String emergencyContact){
            this.emergencyContact = emergencyContact;
            return this;
        }

        public Patient build(){
            if(firstName == null || firstName.isEmpty()){
                throw new IllegalStateException("First name is required");
            }

            if(lastName == null || lastName.isEmpty()){
                throw new IllegalStateException("Last name is required");
            }

            if(pesel == null || pesel.length() != 11){
                throw new IllegalStateException("PESEL must have 11 digits");
            }

            return new Patient(this);
        }

    }




}
