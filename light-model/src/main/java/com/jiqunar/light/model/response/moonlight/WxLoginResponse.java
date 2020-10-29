package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 微信登录响应实体
 */
@Data
@ApiModel(value = "微信登录响应实体", description = "微信登录响应实体")
public class WxLoginResponse {
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private WechatUserInfo userInfo;

    @Data
    @AllArgsConstructor
    public static class WechatUserInfo {
        /**
         * 用户昵称
         */
        private String nickName;
        /**
         * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），
         * 用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效
         */
        private String avatarUrl;
    }
}
