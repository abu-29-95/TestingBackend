package org.example.Lesson5.api;

import org.example.Lesson5.dto.CategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriesService {
    @GET("categories/{id}")
    Call<CategoryResponse> getCategory (@Path("id") int id);
}
