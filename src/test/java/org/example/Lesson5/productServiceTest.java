package org.example.Lesson5;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.example.Lesson5.api.ProductService;
import org.example.Lesson5.dto.Product;
import org.example.Lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class productServiceTest {
    static ProductService productService;
    Faker faker = new Faker();
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll(){
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setProduct(){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));
    }

    @SneakyThrows
    @Test
    void CreatProduct() {
        Response<Product> responseOnCreation = productService.creatProduct(product).execute();

        id  = responseOnCreation.body().getId();

        productService.getProductById(id).execute();

        assertThat(responseOnCreation.isSuccessful(), CoreMatchers.is(true));
        assertThat(responseOnCreation.headers().get("connection"), CoreMatchers.equalTo("keep-alive"));
        assertThat(responseOnCreation.body().getId(), CoreMatchers.equalTo(id));
        assertThat(responseOnCreation.body().getTitle(), CoreMatchers.equalTo(product.getTitle()));
        assertThat(responseOnCreation.body().getPrice(), CoreMatchers.equalTo(product.getPrice()));
        assertThat(responseOnCreation.body().getCategoryTitle(), CoreMatchers.equalTo("Food"));


       Response<Product> responseOnChange = productService.modifyProduct(product
                       .withId(id)
                       .withTitle(product.getTitle())
                       .withCategoryTitle(product.getCategoryTitle())
                       .withPrice(2220)
       ).execute();


         assertThat(responseOnChange.isSuccessful(), CoreMatchers.is(true));
         assertThat(responseOnCreation.body().getId(), CoreMatchers.equalTo(id));
         assertThat(responseOnCreation.body().getTitle(), CoreMatchers.equalTo(product.getTitle()));
         assertThat(responseOnChange.body().getPrice(), CoreMatchers.equalTo(2220));

        Response<ResponseBody> response = productService.getProducts().execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.headers().get("connection"), CoreMatchers.equalTo("keep-alive"));


    }


    @SneakyThrows
    @AfterEach
    void deleteProduct() {
        Response<ResponseBody> response= productService.deleteProduct(id).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


}
