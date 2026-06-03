package pl.kinga.kolekcjegeneryki.additionalexercisesmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionRegistry {
    public static void main(String[] args) {

        Map<String, List<String>> permissions = new HashMap<>();

        permissions.put("ADMIN", new ArrayList<>(List.of("READ", "WRITE", "DELETE", "MANAGE_USERS")));
        permissions.put("EDITOR", new ArrayList<>(List.of("READ", "WRITE")));
        permissions.put("VIEWER", new ArrayList<>(List.of("READ")));

        for (Map.Entry<String, List<String>> entry : permissions.entrySet()) {
            System.out.println("Role: " + entry.getKey());
            for (String permission : entry.getValue()) {
                System.out.println(" - " + permission);
            }
        }

        permissions.get("EDITOR").add("EXPORT");

        permissions.putIfAbsent("AUDITOR",new ArrayList<>(List.of("READ", "AUDIT")));

        int count = 0;
        for (List<String> per : permissions.values()) {
            count += per.size();
        }
        System.out.println("Total amount of permissions: " + count);

        for (Map.Entry<String, List<String>> entry : permissions.entrySet()) {
            System.out.println(entry.getKey());
            for (String perm : entry.getValue()) {
                System.out.println(" - " + perm);
            }
        }

    }
}
