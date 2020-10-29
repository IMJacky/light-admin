package com.jiqunar.light.model.entity.moonlight;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 微信用户信息
 *
 * @author auto generator
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wx_user")
@ApiModel(value = "WxUserEntity对象", description = "微信用户信息")
public class WxUserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    @TableField("open_id")
    private String openId;

    /**
     * 微信unionid
     */
    @ApiModelProperty(value = "微信unionid")
    @TableField("union_id")
    private String unionId;

    /**
     * 最近访问时间
     */
    @ApiModelProperty(value = "最近访问时间")
    @TableField("last_visit_date")
    private LocalDateTime lastVisitDate;

    /**
     * 微信sessionkey
     */
    @ApiModelProperty(value = "微信sessionkey")
    @TableField("session_key")
    private String sessionKey;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称")
    @TableField("nick_name")
    private String nickName;

    /**
     * 语言 en：英文，zh_CN：简体中文，zh_TW：繁体中文
     */
    @ApiModelProperty(value = "语言 en：英文，zh_CN：简体中文，zh_TW：繁体中文")
    @TableField("language")
    private String language;

    /**
     * 用户所在国家
     */
    @ApiModelProperty(value = "用户所在国家")
    @TableField("country")
    private String country;

    /**
     * 用户所在省份
     */
    @ApiModelProperty(value = "用户所在省份")
    @TableField("province")
    private String province;

    /**
     * 用户所在城市
     */
    @ApiModelProperty(value = "用户所在城市")
    @TableField("city")
    private String city;

    /**
     * 性别 0：未知，1：男，2：女
     */
    @ApiModelProperty(value = "性别 0：未知，1：男，2：女")
    @TableField("gender")
    private Integer gender;


}
