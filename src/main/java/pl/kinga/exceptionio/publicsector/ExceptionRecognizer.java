package pl.kinga.exceptionio.publicsector;

import java.util.ArrayList;
import java.util.List;

public class ExceptionRecognizer {

    static void causeNullPointer() {
        String s = null;
        s.length();
    }

    static void causeIndexOutOfBounds() {
        List<String> ar = new ArrayList<>(List.of("Kinia", "Pinia", "Binia"));
        ar.get(3);
    }

    static void causeNumberFormat() {
        Integer.parseInt("abc");
    }

    static void causeClassCast() {
        Object obj = "text";
        Integer num = (Integer) obj;
    }

    static void causeStackOverflow() {
        causeStackOverflow();
    }

    static void causeArithmetic() {
        int result = 10 / 0;
    }

    public static void main(String[] args) {

        //causeNullPointer(); // Type: unchecked (RuntimeException) -> NullPointerException
        //causeIndexOutOfBounds();//Type: unchecked (RuntimeException) ->
        // IndexOutOfBoundsException
        //causeNuberFormat(); // Type: unchecked (RuntimeException) -> NumberFormatException
        //causeClassCast(); //Type: unchecked (RuntimeException) -> ClassCastException
        //causeStackOverflow(); // Type: Error -> StackOverflowError
        //causeArithmetic(); // Type: unchecked (RuntimeException) -> ArithmeticException


    }
}
