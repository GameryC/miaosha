package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * @author yangchen
 * @create 2020-06-03-15:48
 */
public interface OrderService {
    //使用：1.通过前端的 url 上传过来秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始
    //2. 直接在下单接口内判断对应的商品是否存在秒杀活动，若存在进行中的则以秒杀价格下单
    //创建订单 请求参数的请求体 用户ID 购买的商品ID 购买商品数量
    OrderModel creatOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
