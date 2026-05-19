package pl.kinga.oop.finalproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistrationOffice {
    private String officeName;
    private List<Resident> residents;

    public RegistrationOffice(String officeName) {
        if (officeName == null || officeName.isEmpty()) {
            throw new IllegalArgumentException("Input value is incorrect");
        }
        this.officeName = officeName;
        this.residents = new ArrayList<>();
    }

    public void registerResident(Resident resident) {
        if (resident == null) {
            throw new IllegalArgumentException("Input value si incorrect");
        }
        for (Resident resident1 : residents) {
            if (resident1.getPesel().equals(resident.getPesel())) {
                System.out.println("You cannot add this resident");
                return;
            }
        }
        residents.add(resident);
    }

    public Resident findByPesel(String pesel) {
        for (Resident resident : residents) {
            if (resident.getPesel().equals(pesel)) {
                return resident;
            }
        }
        return null;
    }

    public List<Resident> findByQuery(String query) {
        List<Resident> matchingResidents = new ArrayList<>();
        for (Resident resident : residents) {
            if (resident.matchQuery(query)) {
                matchingResidents.add(resident);
            }
        }
        return matchingResidents;
    }

    public List<Resident> getAllResidents() {
        return Collections.unmodifiableList(residents);
    }

    public int getResidentCount() {
        return residents.size();
    }

    @Override
    public String toString() {
        return "RegistrationOffice{" +
                "name='" + officeName + '\'' +
                ", residents=" + getResidentCount() +
                '}';
    }
}
