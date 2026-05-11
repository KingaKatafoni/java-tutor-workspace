package pl.kinga.oop.publicsector;

import java.util.ArrayList;
import java.util.List;

public class VoterRegistry {
    private String districtName;
    private List<String> voters;

    public VoterRegistry(String districtName) {
        this.districtName = districtName;
        this.voters = new ArrayList<>();
    }

    public void addVoter(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name should not be null or empty!");
            return;
        }
        voters.add(name);
    }

    public String getDistrictName() {
        return districtName;
    }

    public List<String> getVoters() {
        return new ArrayList<>(voters);
    }

    public int getVoterCount() {
        return voters.size();
    }

    public boolean hasVoter(String name) {
        return voters.contains(name);
    }

    @Override
    public String toString() {
        return "VoterRegistry{" +
                "districtName='" + districtName + '\'' +
                ", voters='" + getVoterCount() +
                '}';
    }
}
