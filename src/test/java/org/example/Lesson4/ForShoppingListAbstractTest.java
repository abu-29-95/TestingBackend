package org.example.Lesson4;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ForShoppingListAbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrlMealPLanShopList;
    private static String userName;
    private static String hash;
    private static ResponseSpecification responseSpecification;
    private static RequestSpecification requestSpecification;


    @BeforeAll
    static void initTest() throws IOException{
        configFile = new FileInputStream("src/main/resources/forLesson3.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrlMealPLanShopList = prop.getProperty("baseUrlMealPLanShopList");
        userName = prop.getProperty("userName");
        hash = prop.getProperty("hash");

        responseSpecification= new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Connection", "keep-alive")
                .expectContentType(ContentType.JSON)
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey",getApiKey())
                .addQueryParam("hash", getHash())
                .addPathParams("username", getUserName())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrlMealPLanShopList() {
        return baseUrlMealPLanShopList;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getHash(){ return hash;}

    public static ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
