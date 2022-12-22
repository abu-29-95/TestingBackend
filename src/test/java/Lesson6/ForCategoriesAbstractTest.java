package Lesson6;

import Lesson5.api.CategoriesService;
import Lesson5.utils.RetrofitUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public abstract class ForCategoriesAbstractTest {
      private static CategoriesService categoriesService;

     private static SqlSession session;



    @BeforeAll
    static void beforeAll() throws IOException {
        setCategoriesService();
        setSession();

    }

    public static CategoriesService getCategoriesService() {
        return categoriesService;
    }

    public static CategoriesService setCategoriesService() {
        categoriesService = RetrofitUtils.getRetrofit().create(CategoriesService.class);
        return categoriesService;
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


    @AfterAll
    static void afterAll(){
        session.close();
    }



}
