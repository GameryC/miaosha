package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
 * @author yangchen
 * @create 2020-06-03-17:33
 */
public interface PromoService {
    PromoModel getPromoByItemId(Integer itemId);
}
