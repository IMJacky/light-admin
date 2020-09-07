package com.jiqunar.light.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jieguang.wang
 * @date 2020/9/7 16:59
 */
public enum OperateTypeEnum {
    Add(0, "新增"),
    Update(1, "修改"),
    Delete(2, "删除");

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

    OperateTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (OperateTypeEnum relation : OperateTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation.getMsg();
            }
        }
        return "";
    }

    public static OperateTypeEnum getOperateTypeEnum(Integer code) {
        for (OperateTypeEnum relation : OperateTypeEnum.values()) {
            if (relation.getCode().equals(code)) {
                return relation;
            }
        }
        return null;
    }
}
