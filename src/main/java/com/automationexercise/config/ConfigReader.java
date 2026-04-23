package com.automationexercise.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("CRITICAL: 'config.properties' not found in src/test/resources/");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("CRITICAL: missing value for config key: '" + key + "' in 'config.properties'");
        }
        return value.trim();
    }

    public static long getLongProperty(String key) {
        return Long.parseLong(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
