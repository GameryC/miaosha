package com.miaoshaproject.error;

/**
 * @author yangchen
 * @create 2020-05-23-19:36
 */
public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
