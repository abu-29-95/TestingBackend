package org.example.Lesson5.api;

import okhttp3.ResponseBody;
import org.example.Lesson5.dto.Product;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;



public interface ProductService {
     @GET("products")
     Call<ResponseBody> getProducts();

     @POST("products")
    Call<Product> creatProduct (@Body Product creatProductRequest);

     @PUT("products")
    Call<Product> modifyProduct (@Body Product modifyProductReqest);

     @GET("products/{id}")
    Call<Product> getProductById (@Path("id") int id);

     @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct (@Path("id") int id);

}
