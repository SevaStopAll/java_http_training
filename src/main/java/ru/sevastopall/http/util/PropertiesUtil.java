package ru.sevastopall.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = Properties.class.getClassLoader().getResourceAsStream("C:\\Users\\Мы\\IdeaProjects\\java_http_training\\src\\main\\resources\\application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {}

    public static String get(String key) {
        return (String) PROPERTIES.get(key);
    }
}
