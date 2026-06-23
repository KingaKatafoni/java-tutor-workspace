package pl.kinga.funkcyjnajava.lekcja5_8.publicsector;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResidentRegistry {
    public static void main(String[] args){
        List<Resident> residents = List.of(
                new Resident("98273882123", "Anna", "Kowalczyk", 54, "Warszawa", "Ursynow", true),
                new Resident("99273882345", "Zygmunt", "Ambra", 99, "Warszawa", "Wilanow", false),
                new Resident("97273882456", "Waldemar", "Polk", 26, "Warszawa", "Praga", true),
                new Resident("89273882567", "Andrzej", "Bania", 29, "Warszawa", "Powisle", false),
                new Resident("65273882678", "Aniela", "Dydo", 33, "Poznan", "Rataje", true),
                new Resident("22273882789", "Radowslaw", "Eter", 4, "Poznan", "Wilda", true),
                new Resident("56273882890", "Barbara", "Fit", 44, "Poznan", "Jezyce", true),
                new Resident("66273882012", "Maurycy", "Gal", 58, "Poznan", "Piatkowo", false),
                new Resident("78273882234", "Michalina", "Gryk", 79, "Konin", "Zatorze", false),
                new Resident("23273882567", "Leopold", "Kasprzyk", 3, "Konin", "Chorzen", true),
                new Resident("87273882890", "Monika", "Cison", 24, "Konin", "Zakole", true),
                new Resident("84273882065", "Lucyna", "Zygryd", 36, "Konin", "V Osiedle", true),
                new Resident("19273882456", "Orest", "Wojt", 7, "Warszawa", "Ursus", false),
                new Resident("45273882345", "Anna", "Dymna", 19, "Poznan", "Rataje", true)
        );

        System.out.println("----1# Name list >=18 alphabetically----");
        List<String> namesAdultsOnly = residents.stream()
                .filter(r->r.age()>=18)
                .map(Resident::lastName)
                .sorted()
                .toList();
        namesAdultsOnly.forEach(System.out::println);


        System.out.println("----2# Unique cities----");
        Set<String> uniqueCities = residents.stream()
                .map(Resident::city)
                .collect(Collectors.toSet());
        uniqueCities.forEach(System.out::println);

        System.out.println("----3# Pesel name, lastName----");
        Map<String, String> peselNames = residents.stream()
                .collect(Collectors.toMap(Resident::pesel, r-> r.firstName() + " " + r.lastName()));
        peselNames.forEach((key, value)-> System.out.println(key + " → " + value));

        System.out.println("----4# City amount of residents----");
        Map<String, Integer> residentsAmountPerCity = residents.stream()
                .collect(Collectors.toMap(
                        Resident::city, r -> 1, Integer::sum
                ));
        residentsAmountPerCity.forEach((k,v) -> System.out.println(k + " → " + v));

        System.out.println("----5# District + oldest citizen----");
        Map<String, Integer> oldestPlusDistrict = residents.stream()
                .collect(Collectors.toMap(Resident::district, Resident::age, Integer::max));
        oldestPlusDistrict.forEach((k,v)-> System.out.println(k + " → " + v));

        System.out.println("----6# Names of voters----");
        String voters = residents.stream()
                .filter(Resident::registeredVoter)
                .map(Resident::firstName)
                .collect(Collectors.joining(", "));
        System.out.println(voters);

        System.out.println("----7# All residents----");
        String allResidents = residents.stream()
                .map(r-> r.lastName() + " " + r.firstName() + " (" + r.city() + ")")
                .collect(Collectors.joining("\n"));
        System.out.println(allResidents);
    }
}
