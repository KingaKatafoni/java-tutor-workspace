package pl.kinga.kolekcjegeneryki.healthcare.healthcaredeque;

import java.util.LinkedList;
import java.util.Queue;

public class PatientQueue {
    private Queue<Patient> queue;

    public PatientQueue() {
        this.queue = new LinkedList<>();
    }

    public void registerPatient(Patient p) {
        queue.offer(p);
    }

    public Patient callNextPatient() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty!");
            return null;
        }
        return queue.poll();
    }

    public Patient peekNextPatient() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty!");
            return null;
        }
        return queue.peek();
    }

    public int getWaitingCount() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void printQueue() {
        for (Patient p : queue) {
            System.out.println(p);
        }
    }
}
