package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

public class CitizenDataValidator {


    public void validatePesel(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL must have 11 digits");
        }

        if (!pesel.matches("\\d{11}")) {
            throw new IllegalArgumentException("PESEL must contain only digits");
        }
    }

    public void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
    }

    public void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name is mandatory");
        }
    }

    public void validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is mandatory");
        }
    }
}
