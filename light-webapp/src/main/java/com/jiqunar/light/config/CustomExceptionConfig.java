package com.jiqunar.light.config;

import com.jiqunar.light.model.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author jieguang.wang
 * @date 2020/5/7 16:57
 */
@RestControllerAdvice
public class CustomExceptionConfig {
    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse systemException(RuntimeException e) {
        if (StringUtils.isBlank(e.getMessage())) {
            //未找到对应路径的报错？
            return BaseResponse.systemException("not found exception detail");
        } else {
            return BaseResponse.systemException(e.getMessage());
        }
    }

    /**
     * form-data参数校验
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBindException(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return BaseResponse.invalidParams(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * JSON参数校验(requestbody)
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        return BaseResponse.invalidParams(String.join(",", allErrors.stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList())));
    }

    /**
     * 参数校验(requestparame)
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public BaseResponse constraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        return BaseResponse.invalidParams(String.join(",", violations.stream().map(m -> m.getMessage()).collect(Collectors.toList())));
    }

    /**
     * 缺少请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return BaseResponse.invalidParams("缺少请求参数");
    }

    /**
     * 参数有误(post方法的body中没有信息)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return BaseResponse.invalidParams("参数有误");
    }
}
