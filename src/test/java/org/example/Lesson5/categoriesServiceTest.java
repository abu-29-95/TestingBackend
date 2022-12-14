package org.example.Lesson5;

import lombok.SneakyThrows;
import org.example.Lesson5.api.CategoriesService;
import org.example.Lesson5.dto.CategoryResponse;
import org.example.Lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class categoriesServiceTest {

    static CategoriesService categoriesService;

    @BeforeAll
    static void beforeAll(){
        categoriesService = RetrofitUtils.getRetrofit().create(CategoriesService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdTest(){
        Response<CategoryResponse> response = categoriesService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));

    }
}
