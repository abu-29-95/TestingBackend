package org.example.Lesson3;

import io.restassured.http.ContentType;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;


public class TestRestAPI extends AbstractTest {


    @Test
    void getCuisine() {
        given()
                .when()
                .get(getUrlSearchRecipes() + "?cuisine=italian&apiKey=" + getApiKey())
                .then()
                .statusCode(200);


    }

    @Test
    void getDiet() {

        given()
                .request(Method.GET, getUrlSearchRecipes() +
                        "?diet{a}&apiKey={apiKey}", "vegetarian", getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getMinCarbs() {

        given()
                .request(Method.GET, getUrlSearchRecipes() +
                        "?minCarbs{a}&apiKey={apiKey}", "10", getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getAddRecipeInformation() {

       given()
               .queryParam("apiKey", getApiKey())
               .queryParam("addRecipeInformation", "true")
               .response()
               .statusCode(200)
               .header("Connection", "keep-alive")
               .contentType(ContentType.JSON)
               .when()
               .get(getUrlSearchRecipes());
    }

    @Test
    void getRecipeBoxID() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("recipeBoxId", "2468")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .get(getUrlSearchRecipes());
    }


    @Test
    void getMaxCarbs() {

        given()
                .request(Method.GET, getUrlSearchRecipes() +
                        "?maxCarbs{a}&apiKey={apiKey}", "150", getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getSort() {

        given()
                .request(Method.GET, getUrlSearchRecipes() +
                        "?sort{a}&apiKey={apiKey}", "calories", getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(16823L));
    }

    @Test
    void getMaxProtein() {

        given()
                .request(Method.GET, getUrlSearchRecipes() +
                        "?maxProtein{a}&apiKey={apiKey}", "110", getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getMinCalories() {

        given()
                .get( getUrlSearchRecipes() +
                        "?minCalories=110&apiKey="+ getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);


    }

    @Test
    void getMaxCalories() {

        given()
                .get( getUrlSearchRecipes() +
                        "?maxCalories=700&apiKey="+ getApiKey())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void getDietAndMinVitaminA() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("diet", "vegetarian")
                .queryParam("minVitaminA", 50)
                .when()
                .get(getUrlSearchRecipes())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getMinFatAndMaxVitaminC() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minFat", 10)
                .queryParam("minVitaminC", 100)
                .when()
                .get(getUrlSearchRecipes())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);
    }

    @Test
    void getCuisineAndFillIngredients() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("fillIngredients", true)
                .when()
                .get(getUrlSearchRecipes())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void getDietaAndAddRecipeInformation() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("diet", "medicinal")
                .queryParam("addRecipeInformation", "true")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .get(getUrlSearchRecipes());

    }

    @Test
    void postRubenSandwichRecipe() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "ruben sandwich")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(49291L))
                .when()
                .post(getUrlClassifyCuisine());

    }

    @Test
    void postRoastBeEfRecipe() {

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "roast beef")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(158719L))
                .when()
                .post(getUrlClassifyCuisine());

    }

    @Test
    void postThreeOzBeefShoulder() {

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "3 oz beef shoulder")
                .when()
                .post(getUrlClassifyCuisine())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void postBunWithGravy() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "bun with gravy")
                .when()
                .post(getUrlClassifyCuisine())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void postClamChowder() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Clam chowder")
                .when()
                .post(getUrlClassifyCuisine())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void postGomboSoup() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Gombo soup")
                .when()
                .post(getUrlClassifyCuisine())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void postCincinnatiChile() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "cincinnati chile")
                .when()
                .post(getUrlClassifyCuisine())
                .then()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    @Test
    void postApplePie() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Apple pie")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .expect()
                .body("cuisine",equalTo("American"))
                .when()
                .post(getUrlClassifyCuisine());
    }

    @Test
    void postPastaWithCheese() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pasta with chees")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .post(getUrlClassifyCuisine());
    }

    @Test
    void postBuffaloChickenWings() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Buffalo chicken wihgs")
                .response()
                .statusCode(200)
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .post(getUrlClassifyCuisine());
    }












}
