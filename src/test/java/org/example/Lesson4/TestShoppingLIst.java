package org.example.Lesson4;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.RequestForShoppingList;
import org.example.ResponseForShoppingList.ResponseForShoppingList;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestShoppingLIst extends ForShoppingListAbstractTest{

    @Test
    void postAddBakingPowdertoShopList(){
         ResponseForShoppingList response = given().spec(getRequestSpecification())
                .body("{\n"+
                        "\"item\": 1 package baking powder,\n" +
                        "\"aisle\": Baking,\n" +
                        "\"parse\": true\n" +
                        "}")
                .when()
                .post(getBaseUrlMealPLanShopList()+"{username}/shopping-list/items").prettyPeek()
                .then()
                 .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(ResponseForShoppingList.class);
        assertThat(response.getName(), containsString("baking powder"));
        assertThat(response.getAisle(), equalTo("Baking"));
        assertThat(response.getMeasures().getOriginal().getAmount(), equalTo(1.0F));
        assertThat(response.getMeasures().getOriginal().getUnit(), equalTo("package"));

        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrlMealPLanShopList()+"{username}/shopping-list")
               .body()
               .jsonPath();
        assertThat(response.getName(), containsString("baking powder"));
        assertThat(response.getAisle(), equalTo("Baking"));
        assertThat(response.getMeasures().getOriginal().getAmount(), equalTo(1.0F));
        assertThat(response.getMeasures().getOriginal().getUnit(), equalTo("package"));


        String id = response.getId().toString();
        given().spec(getRequestSpecification())
                .when()
                .delete(getBaseUrlMealPLanShopList()+"{username}/shopping-list/items/"+id)
                .then()
                .spec(getResponseSpecification());

    }

    @Test
    void addPotatoestoShopList() throws JsonProcessingException {
        ObjectMapper mapper =new ObjectMapper();

      RequestForShoppingList request = new RequestForShoppingList();
      request.setItem(" 1 kilogram of potatoes");
      request.setAisle("Vegetable");
      request.setParse(true);

      String bodyRequest = mapper.writeValueAsString(request);

      ResponseForShoppingList response = given().spec(getRequestSpecification())
              .body(bodyRequest)
              .when()
              .post(getBaseUrlMealPLanShopList()+"{username}/shopping-list/items").prettyPeek()
              .then()
              .spec(getResponseSpecification())
              .extract()
              .response()
              .body()
              .as(ResponseForShoppingList.class);
      assertThat(response.getName(), equalTo("potatoes"));
      assertThat(response.getAisle(), containsString("Vegetable"));
      assertThat(response.getMeasures().getOriginal().getAmount(), equalTo(1.0F));
      assertThat(response.getMeasures().getOriginal().getUnit(), equalTo("kilogram"));

      given().spec(getRequestSpecification())
              .when()
              .get(getBaseUrlMealPLanShopList()+"{username}/shopping-list")
              .then()
              .spec(getResponseSpecification())
              .extract()
              .response()
              .body()
              .jsonPath();
        assertThat(response.getName(), equalTo("potatoes"));
        assertThat(response.getAisle(), containsString("Vegetable"));
        assertThat(response.getMeasures().getOriginal().getAmount(), equalTo(1.0F));
        assertThat(response.getMeasures().getOriginal().getUnit(), equalTo("kilogram"));

        String id = response.getId().toString();
        given().spec(getRequestSpecification())
                .when()
                .delete(getBaseUrlMealPLanShopList()+"{username}/shopping-list/items/"+id)
                .then()
                .spec(getResponseSpecification());
    }
}

