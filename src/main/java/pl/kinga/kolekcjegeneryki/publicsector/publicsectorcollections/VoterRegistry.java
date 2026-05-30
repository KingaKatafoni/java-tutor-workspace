package pl.kinga.kolekcjegeneryki.publicsector.publicsectorcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoterRegistry {
    private List<String> voters;

    public VoterRegistry() {
        this.voters = new ArrayList<>();
    }

    public void addVoter(String name) {
        voters.add(name);
    }

    public void sortAlphabetically() {
        Collections.sort(voters);
    }

    public void reverseOrder() {
        Collections.reverse(voters);
    }

    public void shuffleForAudit() {
        Collections.shuffle(voters);
    }

    public String getFirst() {
        return Collections.min(voters);
    }

    public String getLast() {
        return Collections.max(voters);
    }

    public int countOccurrences(String name) {
        return Collections.frequency(voters, name);
    }

    public List<String> getVotersReadOnly() {
        return Collections.unmodifiableList(voters);
    }

    public void printAll() {
        for (String voter : voters) {
            System.out.println(voter);
        }
    }
}
