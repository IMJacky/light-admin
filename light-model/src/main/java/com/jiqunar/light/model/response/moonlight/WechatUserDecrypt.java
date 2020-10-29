package com.jiqunar.light.model.response.moonlight;

import lombok.Data;

/**
 * 微信用户解密实体
 * @author jieguang.wang
 * @date 2020/10/23 16:29
 */
@Data
public class WechatUserDecrypt {
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），
     * 用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效
     */
    private String avatarUrl;
    /**
     * 用户性别
     * 值	说明
     * 0	未知
     * 1	男性
     * 2	女性
     */
    private Integer gender;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 语言 en：英文，zh_CN：简体中文，zh_TW：繁体中文
     */
    private String language;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在国家
     */
    private String country;
}
