package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangchen
 * @create 2020-06-03-10:17
 */
@RestController
@RequestMapping("/item")
//@CrossOrigin 可实现跨域请求
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    //创建商品的controller
    @RequestMapping(value = "/create", consumes = {CONTENT_TYPE_FORMED})
    //解析json数据
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        //封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelFromReturn = itemService.createItem(itemModel);

        //返回前端对象模型的封装
        ItemVO itemVO = convertVOFromModel(itemModelFromReturn);

        return CommonReturnType.create(itemVO);
    }


    //商品详情页浏览
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    //解析json数据
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id") Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        //若获取的对应商品 id不存在
//        if (itemModel == null) {
//            throw new BusinessException(EmBussinessError.USER_NOT_EXIST_ITEM);
//        }
        ItemVO itemVO = convertVOFromModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    //商品列表页面浏览
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    //解析json数据
    @ResponseBody
    public CommonReturnType listitem() {
        //使用 stream api 将 list内的 itemMode 转化为 ItemVo
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertVOFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        //将 itemVO 对象 复制到 itemModel对象中
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            //有正在进行或即将进行的秒杀活动
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            // 秒杀活动的适用商品 Id
            itemVO.setPromoId(itemModel.getPromoModel().getItemId());
            // 秒杀活动的开始时间
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            // 秒杀活动的商品价格
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());


        }
        //如果没有商品促销活动
        else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;

    }

}
