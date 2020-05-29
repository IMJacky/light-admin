package com.jiqunar.light.model.response;

import com.jiqunar.light.model.enums.StatusCode;
import lombok.Data;

/**
 * 响应基类
 * @author jieguang.wang
 * @date 2020/5/6 16:08
 */
@Data
public class BaseResponse<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T result;

    public static BaseResponse invalidParams(Object result) {
        return new BaseResponse(StatusCode.InvalidParams.getCode(), StatusCode.InvalidParams.getMsg(), result);
    }

    public static BaseResponse systemException(Object result) {
        return new BaseResponse(StatusCode.SystemException.getCode(), StatusCode.SystemException.getMsg(), result);
    }

    public static BaseResponse success(Object result) {
        return new BaseResponse(StatusCode.Success.getCode(), StatusCode.Success.getMsg(), result);
    }

    public static BaseResponse invalidToken(Object result) {
        return new BaseResponse(StatusCode.InvalidToken.getCode(), StatusCode.InvalidToken.getMsg(), result);
    }

    public BaseResponse(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
