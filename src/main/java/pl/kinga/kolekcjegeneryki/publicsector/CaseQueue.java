package pl.kinga.kolekcjegeneryki.publicsector;

import java.util.LinkedList;
import java.util.List;

public class CaseQueue {
    private List<CaseTicket> cases;

    public CaseQueue() {
        this.cases = new LinkedList<>();
    }

    public void addCase(CaseTicket ticket) {
        cases.add(ticket);// O(1) — LinkedList appends to tail
    }

    public void addUrgentCase(CaseTicket ticket) {
        cases.add(0, ticket);// O(1) — LinkedList inserts at head
    }

    public CaseTicket processNextCase() {
        if (cases.isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        return cases.remove(0); // O(1) — LinkedList removes head
    }

    public CaseTicket peekNextCase() {
        if (cases.isEmpty()) {
            System.out.println("List is empty!");
            return null;
        }
        return cases.get(0); //O(1) the first element has privilege it is head but if we need element in the middle then O(n)
    }

    public Integer getCaseCount() {
        return cases.size();
    }

    @Override
    public String toString() {
        return "CaseQueue{" +
                "cases=" + cases +
                '}';
    }
}
