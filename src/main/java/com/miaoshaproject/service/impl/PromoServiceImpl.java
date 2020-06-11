package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoDOMapper;
import com.miaoshaproject.dataobject.PromoDO;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yangchen
 * @create 2020-06-03-17:35
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ItemService itemService;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        //获取对应商品的秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        // Dataobject -> Model(领域模型)
        PromoModel promoModel = convertFromDataObject(promoDO);
        if (promoModel == null){
            return null;
        }


        //判断当前时间是否秒杀活动即将开始或正在进行
        DateTime now = new DateTime();

        //开始时间比现在还要后面 (未来) 就是还未开始
        if (promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }
        //结束时间 比现在还要前面的话 表示活动已经结束
        else if (promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);
        }else {
            //活动进行中
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    @Override
    public void publishPromo(Integer promoId) {
        //通过活动id获取活动
        PromoDO promoDO = promoDOMapper.selectByPrimaryKey(promoId);
        if (promoDO.getItemId() == null || promoDO.getItemId().intValue() == 0) {
            return;
        }
        ItemModel itemModel = itemService.getItemById(promoDO.getItemId());

        //将库存同步到redis内
        redisTemplate.opsForValue().set("promo_item_stock_" + itemModel.getId(), itemModel.getStock());

        //将大闸的限制数字设到redis内
//        redisTemplate.opsForValue().set("promo_door_count_"+promoId,itemModel.getStock().intValue() * 5);

    }

        private PromoModel convertFromDataObject(PromoDO promoDO){
        if (promoDO == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();

        //将 promoDO 复制给 promoModel
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));

        //将 promoDO 的开始时间(import java.util.Date)  转为(import org.joda.time.DateTime)类型
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        return promoModel;
    }
}
