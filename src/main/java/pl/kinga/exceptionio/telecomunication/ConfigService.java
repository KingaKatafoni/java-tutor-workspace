package pl.kinga.exceptionio.telecomunication;

public class ConfigService {

    public String loadConfig(String filename) throws ConfigurationException {
        if (filename == null || filename.isEmpty()) {
            throw new ConfigurationException("Filename cannot be empty");
        }

        if (!filename.endsWith(".cfg")) {
            throw new ConfigurationException("Invalid config format: " + filename);
        }

        System.out.println("Config loaded: " + filename);
        return filename;
    }

    public void applyConfig(String filename) throws ConfigurationException {
        loadConfig(filename);
        System.out.println("Config applied: " + filename);
    }

    public boolean safeApplyConfig(String filename){
        try {
            loadConfig(filename);
            return true;
        } catch (ConfigurationException e) {
            System.out.println("Failed to load config: " + e.getMessage());
            return false;
        }
    }
}
