package pl.kinga.kolekcjegeneryki.healthcare;

import java.util.*;

public class ClinicManager {
    private Set<Patient> registeredPatients;
    private Queue<Patient> waitingRoom;
    private Map<String, Integer> visitCounter;

    public ClinicManager() {
        this.registeredPatients = new HashSet<>();
        this.waitingRoom = new LinkedList<>();
        this.visitCounter = new HashMap<>();
    }

    public void registerPatient(Patient patient) {
        if (registeredPatients.contains(patient)) {
            System.out.println("You cannot add this patient. Patient already exists!");
            return;
        }
        registeredPatients.add(patient);
    }

    public void addToWaitingRoom(Patient patient) {
        if (registeredPatients.contains(patient)) {
            waitingRoom.add(patient);
        } else {
            System.out.println("Patient should be registered first!");
        }
    }

    public Patient callNextPatient() {
        if (waitingRoom.isEmpty()) {
            System.out.println("Waiting room is empty");
            return null;
        } else {
            Patient firstPatient = waitingRoom.poll();
            Integer amountOfVisits = visitCounter.getOrDefault(firstPatient.pesel(), 0);
            visitCounter.put(firstPatient.pesel(), amountOfVisits + 1);
            return firstPatient;
        }
    }

    public Integer getVisitCount(String pesel) {
        return visitCounter.getOrDefault(pesel, 0);
    }

    public Map<String, Integer> getVisitCounter() {
        return Collections.unmodifiableMap(visitCounter);
    }

    public Integer getWaitingCount() {
        return waitingRoom.size();
    }

    public Integer getRegisteredCount() {
        return registeredPatients.size();
    }

    public void printVisitSummary() {
        for (Map.Entry<String, Integer> entry : visitCounter.entrySet()) {
            System.out.println("Patient: " + entry.getKey() + " amount of visits: " + entry.getValue());
        }
    }

}
