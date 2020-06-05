package com.miaoshaproject.error;

/**
 * @author yangchen
 * @create 2020-05-23-19:38
 */
public enum EmBusinessError implements CommonError {
    // 通用错误类型100001
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),

    UNKNOWN_ERROR(100002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),

    USER_LOGIN_FAIL(20002,"用户手机号或密码不正确"),

    USER_NOT_LOGIN(20003, "账号未登陆，不能下单"),

    //30000开头为交易信息错误
    STOCK_NOT_ENOUGH(30001,"库存不足，去看看其他商品吧"),
;
    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    // 可以定制化修改错误码消息
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
