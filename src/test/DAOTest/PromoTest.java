package DAOTest;

import com.miaoshaproject.App;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.PromoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangchen
 * @create 2020-06-03-19:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PromoTest {

    @Autowired
    private PromoService promoService;

    @Test
    public void testGetPromoByItemId() {
        PromoModel promoByItemId = promoService.getPromoByItemId(11);
        System.out.println(promoByItemId);
    }


}
