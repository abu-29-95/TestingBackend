package org.example.Lesson4;


import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;


public class TestRestAPI extends AbstractTest {
    

    @Test
    void postAddMealPlan() {

       String id = given().spec(getRequestSpecification())
                .queryParam("hash", "9ae4348d3db28d0134411c0ed8a245077ac06a02")
                .body("{\n" +
                        "    \"date\": 1589500800,\n" +
                        "    \"slot\": 1,\n" +
                        "    \"position\": 0,\n" +
                        "    \"type\": \"RECIPE\",\n" +
                        "    \"value\": {\n" +
                        "        \"id\": 296213,\n" +
                        "        \"servings\": 2,\n" +
                        "        \"title\": \"Spinach Salad with Roasted Vegetables and Spiced Chickpea\",\n" +
                        "        \"imageType\": \"jpg\",\n" +
                        "    }\n" +
                        "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/abylkhaiyr/items")
                .then()
               .spec(getResponseSpecification())
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "9ae4348d3db28d0134411c0ed8a245077ac06a02")
                .queryParam("apiKey",getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/abylkhaiyr/items/"+id);
    }


    @Test
    void getCuisine() {
        given().spec(getRequestSpecification())
                .queryParam("cuisine","italian")
                .when()
                .get(getUrlSearchRecipes())
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void getDiet() {
        given().spec(getRequestSpecification())
                .queryParam("diet","vegetarian")
                .when()
                .get(getUrlSearchRecipes())
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void getMinCarbs() {
        given().spec(getRequestSpecification())
                .queryParam("minCarbs", "10")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getAddRecipeInformation() {
       given().spec(getRequestSpecification())
               .queryParam("addRecipeInformation", "true")
               .when()
               .get(getUrlSearchRecipes())
               .then().spec(getResponseSpecification() );
    }

    @Test
    void getRecipeBoxID() {
        given().spec(getRequestSpecification())
                .queryParam("recipeBoxId", "2468")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }


    @Test
    void getMaxCarbs() {
        given().spec(getRequestSpecification())
                .queryParam("maxCarbs", "150")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getSort() {
        given().spec(getRequestSpecification())
                .queryParam("sort", "calories")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getMaxProtein() {
        given().spec(getRequestSpecification())
                .queryParam("maxProtein", "100")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());

    }

    @Test
    void getMinCalories() {

        given().spec(getRequestSpecification())
                .queryParam("minProtein", "80")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getMaxCalories() {
        given().spec(getRequestSpecification())
                .queryParam("maxCalories", "500")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getDietAndMinVitaminA() {

        given().spec(getRequestSpecification())
                .queryParam("diet", "vegetarian")
                .queryParam("minVitaminA", 50)
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getMinFatAndMaxVitaminC() {

        given().spec(getRequestSpecification())
                .queryParam("minFat", 10)
                .queryParam("minVitaminC", 100)
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getCuisineAndFillIngredients() {
        given().spec(getRequestSpecification())
                .queryParam("cuisine", "italian")
                .queryParam("fillIngredients", true)
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void getDietaAndAddRecipeInformation() {
        given().spec(getRequestSpecification())
                .queryParam("diet", "medicinal")
                .queryParam("addRecipeInformation", "true")
                .when()
                .get(getUrlSearchRecipes())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postRubenSandwichRecipe() {

        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "ruben sandwich")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());

    }

    @Test
    void postRoastBeEfRecipe() {
        given().spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "roast beef")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postThreeOzBeefShoulder() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "3 oz beef shoulder")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postBunWithGravy() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "bun with gravy")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());

    }

    @Test
    void postClamChowder() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Clam chowder")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postGomboSoup() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Gombo soup")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postCincinnatiChile() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "cincinnati chile")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }
    @Test
    void postApplePie() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Apple pie")
                .response()
                .expect()
                .body("cuisine",equalTo("American"))
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postPastaWithCheese() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pasta with chees")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }

    @Test
    void postBuffaloChickenWings() {
        given().spec(getRequestSpecification())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Buffalo chicken wihgs")
                .when()
                .post(getUrlClassifyCuisine())
                .then().spec(getResponseSpecification());
    }



}
