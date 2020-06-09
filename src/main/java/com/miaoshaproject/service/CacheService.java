package com.miaoshaproject.service;

/**
 * @author yangchen
 * @create 2020-06-08-19:22
 */
// 封装本地操作类
public interface CacheService {
    //存方法
    void setCommonCache(String key,Object value);

    //取方法
    Object getFromCommonCache(String key);
}
