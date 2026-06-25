package pl.kinga.funkcyjnajava.lekcja5_10.healthcare;

import java.util.List;

public record Clinic(String name, String city, List<Doctor> doctors) {
}
