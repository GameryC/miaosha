package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author yangchen
 * @create 2020-06-02-22:13
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object been){
        final ValidationResult validatotrResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(been);
        if (constraintViolationSet.size() > 0){
            //有错误
            validatotrResult.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation->{
                //错误信息
                String errMsg = constraintViolation.getMessage();
                //获取字段路径
                String propertyName = constraintViolation.getPropertyPath().toString();
                validatotrResult.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return validatotrResult;
    }
    /**
     *  当spring Bean 初始化完成之后，会回调afterPropertiesSet方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //将 Hibernate Validator 通过工厂初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
