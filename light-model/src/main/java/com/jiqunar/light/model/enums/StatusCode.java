package com.jiqunar.light.model.enums;

/**
 * 状态码枚举值
 *
 * @author jieguang.wang
 * @date 2020/5/6 16:18
 */
public enum StatusCode {
    Success(200, "成功"),
    Fail(-1, "失败"),
    InvalidParams(201, "参数无效"),
    InvalidToken(403, "token无效"),
    SystemException(500, "系统异常");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述信息
     */
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
