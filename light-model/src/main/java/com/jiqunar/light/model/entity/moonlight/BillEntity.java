package com.jiqunar.light.model.entity.moonlight;

import java.math.BigDecimal;
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
 * 账单信息
 *
 * @author auto generator
 * @since 2020-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bill")
@ApiModel(value = "BillEntity对象", description = "账单信息")
public class BillEntity extends BaseEntity {

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
     * 交易对方（老板）
     */
    @ApiModelProperty(value = "交易对方（老板）")
    @TableField("boss")
    private String boss;

    /**
     * 商品
     */
    @ApiModelProperty(value = "商品")
    @TableField("product")
    private String product;

    /**
     * 交易单号
     */
    @ApiModelProperty(value = "交易单号")
    @TableField("order_id")
    private String orderId;

    /**
     * 账单日期
     */
    @ApiModelProperty(value = "账单日期")
    @TableField("bill_date")
    private LocalDateTime billDate;

    /**
     * 账单类型（0支出，1收入）
     */
    @ApiModelProperty(value = "账单类型（0支出，1收入）")
    @TableField("bill_type")
    private Integer billType;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @TableField("tag")
    private String tag;

    /**
     * 子标签
     */
    @ApiModelProperty(value = "子标签")
    @TableField("sub_tag")
    private String subTag;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;


}
