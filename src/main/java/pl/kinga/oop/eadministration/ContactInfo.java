package pl.kinga.oop.eadministration;

public record ContactInfo(String email, String phoneNumber) {
    public ContactInfo{
        if (email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty()){
            throw new IllegalArgumentException("Input value is incorrect");
        }
    }
}
