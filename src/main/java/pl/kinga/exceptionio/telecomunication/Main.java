package pl.kinga.exceptionio.telecomunication;

public class Main {
    public static void main(String[] args){
        ConfigService service = new ConfigService();

        service.safeApplyConfig("network.cfg");// ok
        service.safeApplyConfig("wrong.txt");// failed
        service.safeApplyConfig(""); // failed

        try {
            service.applyConfig("database.cfg");    // ok
        } catch (ConfigurationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            service.applyConfig("bad.xml");
        } catch (ConfigurationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
