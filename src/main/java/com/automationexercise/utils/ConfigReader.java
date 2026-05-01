package com.automationexercise.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Global configuration reader. Loads settings from 'config.properties' file once during initialization.
 * */
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

    /**
     * Retrieves a string value for the given key.
     * @param key the property defined in config file
     * @return the trimmed property value as string
     * @throws RuntimeException if the key is missing inside the config file
     * */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("CRITICAL: missing value for config key: '" + key + "' in 'config.properties'");
        }
        return value.trim();
    }

    /**
     * Retrieves a long number value for the given key.
     * @param key the property defined in config file
     * @return the property value as long number
     * @throws RuntimeException if the key is missing inside the config file
     * @throws NumberFormatException if the string does not contain a parsable
     * */
    public static long getLongProperty(String key) {
        return Long.parseLong(getProperty(key));
    }

    /**
     * Retrieves a boolean value for the given key.
     * @param key The property defined in config file
     * @return {@code true} if the value is 'true' and {@code false} for any other value
     * @throws RuntimeException if the key is missing inside the config file
     * */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
