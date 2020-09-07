package com.jiqunar.light.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jieguang.wang
 * @date 2020/9/7 16:59
 */
public enum LogTypeEnum {
    System(1, "系统管理");

    /**
     * 状态码
     */
    @Getter
    @Setter
    private Integer code;

    /**
     * 状态码描述信息
     */
    @Getter
    @Setter
    private String msg;

    LogTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (LogTypeEnum relation : LogTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation.getMsg();
            }
        }
        return "";
    }

    public static LogTypeEnum getLogTypeEnum(Integer code) {
        for (LogTypeEnum relation : LogTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation;
            }
        }
        return null;
    }
}
