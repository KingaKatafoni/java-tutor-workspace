package pl.kinga.exceptionio.publicsector.publicsectorcheckedvsunchecked;

public class Main {
    public static void main(String[] args) {
        CitizenValidator.validatePesel("12345678901");
        //CitizenValidator.validatePesel("123"); // IllegalArgumentException:
        //  PESEL must be 11 characters

        CitizenValidator.validateAge(35);
        //CitizenValidator.validateAge(156);

        CitizenValidator.validateEmail("kinia@gmail.com");
        //CitizenValidator.validateEmail("kinia.gmail.com");
    }
}
