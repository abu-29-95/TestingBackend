package org.example.Lesson3;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String urlSearchRecipes;
    private static String urlClassifyCuisine;

    @BeforeAll
    static void initTest() throws IOException{
        configFile = new FileInputStream("src/main/resources/forLesson3.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        urlSearchRecipes = prop.getProperty("urlSearchRecipes");
        urlClassifyCuisine = prop.getProperty("urlClassifyCuisine");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getUrlSearchRecipes() {
        return urlSearchRecipes;
    }

    public static String getUrlClassifyCuisine() {
        return urlClassifyCuisine;
    }
}
