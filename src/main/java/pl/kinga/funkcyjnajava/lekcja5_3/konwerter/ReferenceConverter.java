package pl.kinga.funkcyjnajava.lekcja5_3.konwerter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ReferenceConverter {
    public static void main(String[] args) {
        //1.
        Function<String, String> toLower = String::toLowerCase;
        System.out.println(toLower.apply("Kinga"));
        //2.
        Function<String, Integer> toLength = String::length;
        System.out.println(toLength.apply("Kinga"));
        //3.
        Consumer<String> printer = System.out::println;
        printer.accept("Kinga Binga");
        //4.
        Function<String, BigDecimal> toDecimal = BigDecimal::new;
        System.out.println(toDecimal.apply("1345"));
        //5.
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test("222"));
        //6.
        Supplier<List<String>> newList = ArrayList::new;
        System.out.println(newList.get());
        //7.
        Function<String, String> toUpper = s -> s.toUpperCase();
        System.out.println(toUpper.apply("Kinga"));
        //8.
        Consumer<Object> print = o -> System.out.println(o);
        print.accept("Second print");
        //9.
        Function<String, Integer> parse = p -> Integer.parseInt(p);
        System.out.println(parse.apply("23"));
        //10.
        Function<String, String> addPrefix = s -> "PL-" + s; // nie da sie zmienic bo mamy konkatenacje
        System.out.println(addPrefix.apply("Kinia"));
        //11.
        Predicate<Integer> isPositive = n -> n > 0;// nie da sie zamienic bo mamy warunek logiczny
        System.out.println(isPositive.test(-23));
        //12.
        Function<String, String> trim = String::trim;
        System.out.println(trim.apply(" Kinga Binga 2 "));
    }
}
