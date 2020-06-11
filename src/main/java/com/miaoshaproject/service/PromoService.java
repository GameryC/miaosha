package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * @author yangchen
 * @create 2020-06-03-17:33
 */
public interface PromoService {
    //根据itemid获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);

    //活动发布
    void publishPromo(Integer promoId);
}
