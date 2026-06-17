package pl.kinga.funkcyjnajava.lekcja5_1.publicsector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CitizenService {
    public static List<Citizen> filter(List<Citizen> citizens, Predicate<Citizen> condition){
        List<Citizen> result = new ArrayList<>();
        for (Citizen c : citizens){
            if(condition.test(c)){
                result.add(c);
            }
        }
        return result;
    }

    public static List<String> transform(List<Citizen> citizens, Function<Citizen, String> mapper){
        List<String> results = new ArrayList<>();
        for (Citizen c : citizens){
            results.add(mapper.apply(c));
        }
        return results;
    }

    public static void printEach(List<Citizen> citizens, Consumer<Citizen> action){
        for (Citizen citizen : citizens){
            action.accept(citizen);
        }
    }

    public static Citizen createDefault(Supplier<Citizen> supplier){
        return supplier.get();
    }

    public static void main(String[] args){
        List<Citizen> citizens = new ArrayList<>();
        citizens.add(new Citizen("98081234567", "Kryspin", "Dydko", "Konin", 34, "ALIVE"));
        citizens.add(new Citizen("90080934576", "Adi", "Albreht", "Krakow", 76, "DEAD"));
        citizens.add(new Citizen("91081334539", "Badi", "Walen", "Malbork", 56, "DEAD"));
        citizens.add(new Citizen("95061234977", "Maria", "Kopan", "Warszawa", 36, "ALIVE"));
        citizens.add(new Citizen("98021234957", "Orest", "Papaja", "Poznan", 23, "ALIVE"));
        citizens.add(new Citizen("92012334521", "Albert", "Samos", "Warszawa", 65, "ALIVE"));

        System.out.println("Citizens from Warsaw: " + filter(citizens, c -> c.city().equals("Warszawa")));
        System.out.println("Citizens older than 30 years old: " + filter(citizens, c -> c.age() > 30));
        System.out.println(transform(citizens, c -> c.firstName() + " " + c.lastName()));
        printEach(citizens, c -> System.out.println("[" + c.status() + "] " + c.firstName() + " " + c.lastName() + " (" + c.city() + ")"));
        Citizen defaultCitizen = createDefault(() -> new Citizen("00000000000", "NIEZNANY", "NIEZNANY", "BRAK", 0, "UNKNOWN"));
        System.out.println("Default: " + defaultCitizen);

    }
}
