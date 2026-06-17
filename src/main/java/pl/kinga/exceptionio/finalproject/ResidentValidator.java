package pl.kinga.exceptionio.finalproject;

public class ResidentValidator {

    public static void validate(String[] columns, int lineNumber) throws InvalidResidentDataException {

        if (columns.length != 7) {
            throw new InvalidResidentDataException(lineNumber, "Not enough columns (expected 7, got " + columns.length);
        }

        String pesel = columns[0];
        String firstName = columns[1];
        String lastName = columns[2];
        String city = columns[4];

        if (pesel.isEmpty()) {
            throw new InvalidResidentDataException(lineNumber, "PESEL is empty");
        }
        if (pesel.length() != 11) {
            throw new InvalidResidentDataException(lineNumber, "PESEL must be exactly 11 digits (got: " + pesel.length() + ")");
        }
        if (!pesel.matches("\\d+")) {
            throw new InvalidResidentDataException(lineNumber, "PESEL contains non-digit characters: " + pesel);
        }
        if (firstName.isEmpty()) {
            throw new InvalidResidentDataException(lineNumber, "First name is empty");
        }
        if (lastName.isEmpty()) {
            throw new InvalidResidentDataException(lineNumber, "Last name is empty");
        }
        if (city.isEmpty()) {
            throw new InvalidResidentDataException(lineNumber, "City is empty");
        }
    }
}
