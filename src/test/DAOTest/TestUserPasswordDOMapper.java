package DAOTest;

import com.miaoshaproject.App;
import com.miaoshaproject.dao.UserPasswordDOMapper;
import com.miaoshaproject.dataobject.UserPasswordDO;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.PromoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangchen
 * @create 2020-06-03-21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserPasswordDOMapper {
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Test
    public void testGetPromoByItemId() {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(29);
        System.out.println(userPasswordDO);
    }
}
