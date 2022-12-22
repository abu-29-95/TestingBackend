package Lesson6;

import Lesson5.dto.CategoryResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class catedoriesMapperTest extends ForCategoriesAbstractTest {

    @Test
    void getCategoryByIdTest() throws IOException {
         Response<CategoryResponse> response = getCategoriesService().getCategory(1).execute();

            db.dao.CategoriesMapper categoriesMapper = getSession().getMapper(db.dao.CategoriesMapper.class);
            db.model.CategoriesExample example = new db.model.CategoriesExample();
            example.createCriteria().andIdEqualTo(1);
            List<db.model.Categories> list = categoriesMapper.selectByExample(example);

            assertThat(response.isSuccessful(), CoreMatchers.is(true));
            assertThat(response.body().getId(), equalTo(list.iterator().next().getId()));
            assertThat(response.body().getTitle(), equalTo(list.iterator().next().getTitle()));


    }
}
