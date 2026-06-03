package pl.kinga.exceptionio.fintech;

import java.util.ArrayList;
import java.util.List;

public class ExceptionClassifier {
    public static void main(String[] args) {
        List<String> exceptions = List.of(
                "NullPointerException",
                "IOException",
                "StackOverflowError",
                "FileNotFoundException",
                "IllegalArgumentException",
                "OutOfMemoryError",
                "SQLException",
                "ClassCastException",
                "ArithmeticException",
                "NumberFormatException"
        );

        List<String> errors = new ArrayList<>();
        List<String> checked = new ArrayList<>();
        List<String> unchecked = new ArrayList<>();

        for (String exception : exceptions) {
            if (exception.contains("Error")) {
                errors.add(exception);
            } else if (exception.equals("IOException") || exception.equals("FileNotFoundException") || exception.equals("SQLException")) {
                checked.add(exception);
            } else {
                unchecked.add(exception);
            }
        }

        System.out.println("Errors: " + errors);
        System.out.println("Checked: " + checked);
        System.out.println("Unchecked: " + unchecked);
    }
}
