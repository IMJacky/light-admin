package com.jiqunar.light.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jieguang.wang
 * @date 2020/9/7 16:59
 */
public enum LogSubTypeEnum {
    Menu(1, "菜单"),
    User(2, "用户"),
    Role(3, "角色"),
    Dept(4, "部门"),
    Job(5, "岗位");

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

    LogSubTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (LogSubTypeEnum relation : LogSubTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation.getMsg();
            }
        }
        return "";
    }

    public static LogSubTypeEnum getLogSubTypeEnum(Integer code) {
        for (LogSubTypeEnum relation : LogSubTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation;
            }
        }
        return null;
    }
}
