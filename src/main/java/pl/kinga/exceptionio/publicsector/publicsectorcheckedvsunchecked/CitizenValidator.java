package pl.kinga.exceptionio.publicsector.publicsectorcheckedvsunchecked;

public class CitizenValidator {

    public static void validatePesel(String pesel) {
        if (pesel == null || pesel.isEmpty()) {
            throw new IllegalArgumentException("PESEL cannot be null or empty");
        }

        if (pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL must be 11 characters");
        }

        System.out.println("PESEL valid: " + pesel);
    }

    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
        System.out.println("Age valid: " + age);
    }

    public static void validateEmail(String email) {
        if (email == null) {
            throw new NullPointerException("Email cannot be null");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }

        System.out.println("Email valid: " + email);
    }

}
