package pl.kinga.kolekcjegeneryki.telecommunication.telecommunicationcollections;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> codeList = List.of("PLAY", "ORANGE", "TMOBILE");
        Map<String, String> settings = Map.of("maxConnections", "1000", "timeout", "30s", "region", "EU");

        SystemConfig systemConfig = new SystemConfig(codeList, settings);
        System.out.println("All codes: " + systemConfig.getAllowedCodes());
        //System.out.println(systemConfig.getAllowedCodes().add("PLUS"));//UnsupportedOperationException
        //System.out.println("Settings: " + systemConfig.getSettings().put("Pol", "pi"));// UnsupportedOperationException
        systemConfig.addCode("NETIA");
        System.out.println("-----Configuration-------");
        systemConfig.printConfig();
        System.out.println("-----Allowed codes------");
        System.out.println(systemConfig.getAllowedCodes());


    }
}
