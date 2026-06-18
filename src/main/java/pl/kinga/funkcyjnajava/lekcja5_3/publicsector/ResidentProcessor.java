package pl.kinga.funkcyjnajava.lekcja5_3.publicsector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ResidentProcessor {
    public static List<Resident> convertAll(List<String> inputs, Function<String, Resident> creator) {
        List<Resident> residents = new ArrayList<>();
        for (String resident : inputs) {
            residents.add(creator.apply(resident));
        }
        return residents;
    }

    public static void printFormatted(List<Resident> residents, Function<Resident, String> formatter, Consumer<String> output) {
        for (Resident resident : residents) {
            String formattedResident = formatter.apply(resident);
            output.accept(formattedResident);
        }
    }

    public static void main(String[] args) {
        List<String> rawData = List.of(
                "90010112345,Anna,Kowalska,Warszawa",
                "85030567890,Jan,Wisniewski,Krakow",
                "78112234567,Maja,Lewandowska,Gdansk",
                "95020456789,Adam,Wojciechowski,Poznan"
        );

        List<Resident> residents = convertAll(rawData, s -> {
            String[] parts = s.split(",");
            return new Resident(parts[0], parts[1], parts[2], parts[3]);
        });

        printFormatted(residents, r -> r.firstName() + " " + r.lastName() + " (" + r.city() + ")", System.out::println);
        printFormatted(residents, Resident::pesel, System.out::println);
    }
}
