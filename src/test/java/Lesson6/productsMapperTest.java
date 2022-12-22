package Lesson6;

import Lesson5.dto.Product;
import db.model.CategoriesExample;
import db.model.Products;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class productsMapperTest extends ForProductsAbstractTest {

    static int id;

    @Test
    void creatProduct() throws IOException {
         Response<Product> response = getProductService().creatProduct(getProduct()).execute();

        id= response.body().getId();

        db.dao.ProductsMapper productsMapper = getSession().getMapper(db.dao.ProductsMapper.class);
        db.model.ProductsExample example = new db.model.ProductsExample();

        example.createCriteria().andIdEqualTo(id);
        List<db.model.Products> list = productsMapper.selectByExample(example);

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(),equalTo(list.iterator().next().getId()));
        assertThat(response.body().getTitle(),equalTo(list.iterator().next().getTitle()));
        assertThat(response.body().getPrice(),equalTo(list.iterator().next().getPrice()));
        int categoryId =  list.iterator().next().getCategory_id().intValue();

        db.dao.CategoriesMapper categoriesMapper = getSession().getMapper(db.dao.CategoriesMapper.class);
        db.model.CategoriesExample example2 = new CategoriesExample();
        example2.createCriteria().andIdEqualTo(categoryId);
        List<db.model.Categories> listCategories = categoriesMapper.selectByExample(example2);

        assertThat(response.body().getCategoryTitle(), equalTo(listCategories.iterator().next().getTitle()));


    }




   @SneakyThrows
    @AfterAll
    static void deleteProduct() {
        db.dao.ProductsMapper productsMapper = getSession().getMapper(db.dao.ProductsMapper.class);

        productsMapper.deleteByPrimaryKey(id);
        getSession().commit();
    }




}
