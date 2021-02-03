package com.example.demo.common.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.vo.CommonConstants;
import com.example.demo.common.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@RestControllerAdvice
public class GlobalResponeAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //springboot系统错误不处理
        if (methodParameter.getMethod().getName().equals("error")) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_MSG);

        if (o == null) {
            //说明返回为void
            return response;
        } else if (o instanceof CommonResponse) {
            //如果设置返回类型则强转即可
            response = (CommonResponse<Object>) o;
        }else if (o instanceof String){
            //String类型需要特殊处理，底层源码不能强转
            response.setData(o);
            return JSON.toJSONString(response);
        }else {
            //除上二情况设置data
            response.setData(o);
        }
        return response;
    }
}
