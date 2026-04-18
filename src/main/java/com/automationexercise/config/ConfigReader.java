package com.automationexercise.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties = new Properties();

    public ConfigReader(String filename) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new RuntimeException("file '" + filename + "' not found in src/test/resources/");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("failed to load configuration file: " + filename, e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("missing value for config key: '" + key + "' in 'config.properties'");
        }
        return value.trim();
    }

    public long getLongProperty(String key) {
        return Long.parseLong(getProperty(key));
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
