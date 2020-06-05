package DAOTest;

import com.miaoshaproject.App;
import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dataobject.ItemDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangchen
 * @create 2020-06-03-15:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemDOMapperTest {
    @Autowired
    private ItemDOMapper itemDOMapper;

    @Test
    public void testItemMapper() {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(6);
        System.out.println(itemDO.getPrice());
    }
}
