package pl.kinga.funkcyjnajava.lekcja5_13.ecommerce;

import java.util.Arrays;
import java.util.List;

public class CustomerLookup {
    public static void main(String[] args) {
        List<CustomerProfile> customers = Arrays.asList(
                new CustomerProfile("C001", "Anna Kowalska", "anna@firma.pl",
                        new Address("Dluga 10", "Warszawa", "00-001", "PL"), "500100200"),
                new CustomerProfile("C002", "Jan Nowak", null,
                        new Address("Krotka 5", "Krakow", "30-002", "PL"), null),
                new CustomerProfile("C003", "Maria Wiszniewska", "maria@gmail.com",
                        null, "600300400"),
                new CustomerProfile("C004", "Piotr Zielinski", "piotr@firma.pl",
                        new Address("Szeroka 22", "Poznan", "60-003", "PL"), null),
                new CustomerProfile("C005", "Ewa Dabrowska", null,
                        null, null),
                new CustomerProfile("C006", "Tomasz Lewandowski", "tomek@outlook.com",
                        new Address("Polna 8", "Gdansk", "80-004", "DE"), "700500600")
        );

        System.out.println("---- 1# City of client C001 ----");
        String cityOfCustomerC001 = customers.stream()
                .filter(c -> c.customerId().equals("C001"))
                .findFirst()
                .map(CustomerProfile::address)
                .map(Address::city)
                .orElse("Brak adresu");
        System.out.println(cityOfCustomerC001);

        System.out.println("---- 2# City of client C003 ----");
        String cityOfCustomerC003 = customers.stream()
                .filter(c -> c.customerId().equals("C003"))
                .findFirst()
                .map(CustomerProfile::address)
                .map(Address::city)
                .orElse("Brak adresu");
        System.out.println(cityOfCustomerC003);

        System.out.println("---- 3# Email of client C002 toUpperCase() ----");
        String emailOfClinetC002 = customers.stream()
                .filter(c -> c.customerId().equals("C002"))
                .findFirst()
                .map(CustomerProfile::email)
                .map(String::toUpperCase)
                .orElse("BRAK EMAILA");
        System.out.println(emailOfClinetC002);

        System.out.println("---- 4# Emaill of client C001 if is company ----");
        String emailC001IfCompany = customers.stream()
                .filter(c -> c.customerId().equals("C001"))
                .findFirst()
                .map(CustomerProfile::email)
                .filter(e -> e.endsWith("@firma.pl"))
                .orElse("Brak emaila firmowego");
        System.out.println(emailC001IfCompany);

        System.out.println("---- 5# Emaill of client C006 if is company ----");
        String emailC006IfCompany = customers.stream()
                .filter(c -> c.customerId().equals("C006"))
                .findFirst()
                .map(CustomerProfile::email)
                .filter(e -> e.endsWith("@firma.pl"))
                .orElse("Brak emaila firmowego");
        System.out.println(emailC006IfCompany);

        System.out.println("---- 6# Postal code of client C004 ----");
        String postalCodeC004 = customers.stream()
                .filter(c -> c.customerId().equals("C004"))
                .findFirst()
                .map(CustomerProfile::address)
                .map(Address::zipCode)
                .orElse("Brak");
        System.out.println(postalCodeC004);

        System.out.println("---- 7# Country of client C001 tylko jeśli PL ----");
        String countryC001IfPL = customers.stream()
                .filter(c -> c.customerId().equals("C001"))
                .findFirst()
                .map(CustomerProfile::address)
                .map(Address::country)
                .filter(country -> country.equals("PL"))
                .orElse("Nie-PL lub brak adresu");
        System.out.println(countryC001IfPL);

        System.out.println("---- 8# Country of client C006 tylko jeśli PL ----");
        String countryC006IfPL = customers.stream()
                .filter(c -> c.customerId().equals("C006"))
                .findFirst()
                .map(CustomerProfile::address)
                .map(Address::country)
                .filter(country -> country.equals("PL"))
                .orElse("Nie-PL lub brak adresu");
        System.out.println(countryC006IfPL);

        System.out.println("---- 9# Phone number of client C005 formatted ----");
        String phoneNumberFormatted = customers.stream()
                .filter(c -> c.customerId().equals("C005"))
                .findFirst()
                .map(CustomerProfile::phoneNumber)
                .map(p -> "+48 " + p)
                .orElse("Brak telefonu");
        System.out.println(phoneNumberFormatted);

    }
}
