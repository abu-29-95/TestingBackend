package Lesson6;

import Lesson5.api.ProductService;
import Lesson5.dto.Product;
import Lesson5.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public class ForProductsAbstractTest {
    private static ProductService productService;

    private static SqlSession session;

    static Faker faker = new Faker();

    private static Product product;




    @BeforeAll
    static void beforeAll() throws IOException {
        setProductService();
        setSession();

        setProduct();
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static void setProductService() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    public static SqlSession getSession() {
        return session;
    }

    public static SqlSession setSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        return session;
    }

    public static Product getProduct() {
        return product;
    }


    public static void setProduct (){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));
    }


    @AfterAll
    static void afterAll(){
        session.close();
    }
}
