package com.example.demo.common.advice;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.vo.CommonConstants;
import com.example.demo.common.vo.CommonResponse;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 对未知Exception 进行统一处理
     * ExceptionHandler: 可以对指定的异常进行拦截
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handleException(HttpServletRequest request,
                                                  Exception exception) throws IOException {

        try {
            String parameters =queryParameters(request);
            //日志伪代码
            System.out.println(parameters);
        }catch (Exception e){

        }

        // 优化: 定义不同类型的异常枚举(异常码和异常信息)
        CommonResponse<String> response = new CommonResponse<>(CommonConstants.FAILURE_CODE, "unknown error");

        response.setData(exception.getLocalizedMessage());
        if (response.getData() == null) {
            // 打印错误堆栈信息
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            exception.printStackTrace(new PrintStream(baos));
            response.setData(baos.toString());
        }

        return response;
    }

    /**
     *  校验请求体错误拦截处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<Object> handleException(HttpServletRequest request,
                                                  MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        CommonResponse<Object> response = new CommonResponse<>(CommonConstants.FAILURE_CODE, "body error");
        if (result.hasErrors()) {
            List<FieldError> allErrors = result.getFieldErrors();
            String errors = allErrors.stream()
                    .map(error -> error==null?"null":error.getField()+":"+error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            response.setMessage(errors);
        }
        return response;
    }

    private String queryParameters(HttpServletRequest request) throws IOException {
        String method = request.getMethod().toUpperCase();
        if (method.equals("GET")) {
            Map<String, String[]> map = request.getParameterMap();
            return JSONObject.toJSONString(map);
        } else if (method.equals("POST")) {
            String contentType = request.getHeader("content-type");
            if (contentType.contains("application/json")) {
                InputStream is = request.getInputStream();
                return StreamUtils.copyToString(is, Charset.forName("utf-8"));
            } else {
                // 上传文件之类的,暂时不管
            }
        }
        return null;
    }

    private String trackToString(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElemen : stackTraceElements) {
            sb.append(stackTraceElemen.toString() + "\n");
        }
        return sb.toString();
    }
}
