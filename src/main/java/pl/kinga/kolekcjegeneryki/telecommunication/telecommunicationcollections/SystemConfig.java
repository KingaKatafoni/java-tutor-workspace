package pl.kinga.kolekcjegeneryki.telecommunication.telecommunicationcollections;

import java.util.*;

public class SystemConfig {
    private List<String> allowedOperatorCodes;
    private Map<String, String> settings;

    public SystemConfig(List<String> codes, Map<String, String> settings) {
        this.allowedOperatorCodes = new ArrayList<>(codes);
        this.settings = new HashMap<>(settings);
    }

    public List<String> getAllowedCodes() {
        return Collections.unmodifiableList(allowedOperatorCodes);
    }

    public Map<String, String> getSettings() {
        return Collections.unmodifiableMap(settings);
    }

    public boolean isCodeAllowed(String code) {
        return allowedOperatorCodes.contains(code);
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public void addCode(String code) {
        allowedOperatorCodes.add(code);
    }

    public void printConfig() {
        for (Map.Entry<String, String> entry : settings.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}

