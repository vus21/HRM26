package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    } 

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}