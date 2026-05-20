package pl.kinga.kolekcjegeneryki.healthcare;

import java.util.Objects;

public record Patient(String pesel, String fullName) {
    public Patient {
        if (pesel == null || pesel.length() != 11 || fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient other = (Patient) obj;
        return pesel.equals(other.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
