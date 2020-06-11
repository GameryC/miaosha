package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

/**
 * @author yangchen
 * @create 2020-05-23-14:16
 */
public interface UserService {
    //用户id 获得 用户对象
    UserModel getUserById(Integer id);

    //在缓存中获取用户信息
    UserModel getUserByIdInCache(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param telephone 用户注册手机
     * @param encrptPassword 用户加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;
}
