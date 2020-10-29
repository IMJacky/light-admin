package com.jiqunar.light.model.request.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信登录请求实体
 */
@Data
@ApiModel(value = "微信登录请求实体", description = "微信登录请求实体")
public class WxLoginRequest {
    /**
     * 微信code
     */
    @ApiModelProperty(value = "微信code")
    private String code;

    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    @ApiModelProperty(value = "不包括敏感信息的原始数据字符串，用于计算签名")
    private String rawData;

    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
     */
    @ApiModelProperty(value = "使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息")
    private String signature;

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据")
    private String encryptedData;

    /**
     * 加密算法的初始向量
     */
    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;
}
