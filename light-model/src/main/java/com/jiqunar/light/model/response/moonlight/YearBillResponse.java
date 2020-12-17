package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 年度账单实体
 *
 * @author jieguang.wang
 * @date 2020/12/10 15:55
 */
@Data
@ApiModel(value = "年度账单实体", description = "年度账单实体")
public class YearBillResponse {
    /**
     * 加密的openid
     */
    @ApiModelProperty(value = "加密的openid")
    private String encryptOpenId;
    /**
     * 交易总数量
     */
    @ApiModelProperty(value = "交易总数量")
    private Integer yearBillCount;

    /**
     * 交易总额
     */
    @ApiModelProperty(value = "交易总额")
    private BigDecimal yearBillAmount;

    /**
     * 支付宝交易总数量
     */
    @ApiModelProperty(value = "支付宝交易总数量")
    private Integer yearBillAlipayCount;

    /**
     * 支付宝交易总额
     */
    @ApiModelProperty(value = "支付宝交易总额")
    private BigDecimal yearBillAlipayAmount;

    /**
     * 微信支付交易总数量
     */
    @ApiModelProperty(value = "微信支付交易总数量")
    private Integer yearBillWepayCount;

    /**
     * 微信支付交易总额
     */
    @ApiModelProperty(value = "微信支付交易总额")
    private BigDecimal yearBillWepayAmount;

    /**
     * 支出总数量
     */
    @ApiModelProperty(value = "支出总数量")
    private Integer yearExpenseCount;

    /**
     * 支出总额
     */
    @ApiModelProperty(value = "支出总额")
    private BigDecimal yearExpenseAmount;

    /**
     * 收入总数量
     */
    @ApiModelProperty(value = "收入总数量")
    private Integer yearEarningCount;

    /**
     * 收入总额
     */
    @ApiModelProperty(value = "收入总额")
    private BigDecimal yearEarningAmount;

    /**
     * 支出最多的月份
     */
    @ApiModelProperty(value = "支出最多的月份")
    private Integer monthExpenseMost;

    /**
     * 支出最多的月份的金额
     */
    @ApiModelProperty(value = "支出最多的月份的金额")
    private BigDecimal monthExpenseMostAmount;

    /**
     * 光顾最多的老板的总数量
     */
    @ApiModelProperty(value = "光顾最多的老板的总数量")
    private Integer bossMostCount;

    /**
     * 光顾最多的老板
     */
    @ApiModelProperty(value = "光顾最多的老板")
    private String bossMost;

    /**
     * 淘宝购买的总数量
     */
    @ApiModelProperty(value = "淘宝购买的总数量")
    private Integer tbExpenseCount;

    /**
     * 淘宝购买的总额
     */
    @ApiModelProperty(value = "淘宝购买的总额")
    private BigDecimal tbExpenseAmount;

    /**
     * 发送的微信红包总数量
     */
    @ApiModelProperty(value = "发送的微信红包总数量")
    private Integer wxhbSendCount;

    /**
     * 发送的微信红包总额
     */
    @ApiModelProperty(value = "发送的微信红包总额")
    private BigDecimal wxhbSendAmount;

    /**
     * 收到的微信红包总数量
     */
    @ApiModelProperty(value = "收到的微信红包总数量")
    private Integer wxhbReceiveCount;

    /**
     * 收到的微信红包总额
     */
    @ApiModelProperty(value = "收到的微信红包总额")
    private BigDecimal wxhbReceiveAmount;
}
