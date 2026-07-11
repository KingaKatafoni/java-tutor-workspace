package pl.kinga.testowanie.lekcja7_2;

public class CitizenValidator {

    public boolean validatePesel(String pesel) {
        if (pesel == null) {
            throw new IllegalArgumentException("PESEL cannot be null");
        }

        if (pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL must be 11 digits");
        }

        if (!pesel.matches("\\d+")) {
            throw new IllegalArgumentException("PESEL must contain only digits");
        }

        return true;
    }

    public String validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        if (age > 150) {
            throw new IllegalArgumentException("Age exceeds maximum");
        }

        if (age < 18) {
            return "MINOR";
        } else {
            return "ADULT";
        }
    }

    public String formatFullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        String lastNameUpper = lastName.toUpperCase();
        String nameCapitalized = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();

        return lastNameUpper + " " + nameCapitalized;
    }
}
