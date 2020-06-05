package DAOTest;

import com.miaoshaproject.App;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangchen
 * @create 2020-06-03-15:04
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void testSelect() {
        ItemModel item = itemService.getItemById(6);
        System.out.println(item);
//        System.out.println("success");
    }
}
