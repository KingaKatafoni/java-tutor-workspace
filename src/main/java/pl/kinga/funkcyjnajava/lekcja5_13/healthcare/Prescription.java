package pl.kinga.funkcyjnajava.lekcja5_13.healthcare;

import java.time.LocalDate;

public record Prescription(String medicationName, String dosage, LocalDate validUntil) {
}
