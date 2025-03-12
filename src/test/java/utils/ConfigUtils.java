package utils;

import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils() {
        properties = PropertiesUtils.loadProperties("src/test/resources/local.properties");
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            System.out.println("Base URL retrieved from config file: " + prop);
            return prop;
        }
        throw new RuntimeException("Could not find the base URL in the property file");
    }

    public String getBrowser() {
        String prop = properties.getProperty("browser");
        if (prop != null) {
            System.out.println("Browser retrieved from config file: " + prop);
            return prop;
        }
        throw new RuntimeException("Could not find the browser in the property file");
    }

    public String getUsername() {
        String prop = properties.getProperty("username");
        if (prop != null) {
            System.out.println("Username retrieved from config file: " + prop);
            return prop;
        }
        throw new RuntimeException("Could not find the username in the property file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) {
            System.out.println("Password retrieved from config file: " + prop);
            return prop;
        }
        throw new RuntimeException("Could not find the password in the property file");
    }

    public boolean isHeadless() {
        String prop = properties.getProperty("headless");
        if (prop != null) {
            System.out.println("Headless mode retrieved from config file: " + prop);
            return Boolean.parseBoolean(prop);
        }
        throw new RuntimeException("Could not find the headless property in the property file");
    }


}
