package com.jiqunar.light.model.response.moonlight;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

/**
 * 微信账单csv
 * @author jieguang.wang
 * @date 2020/10/14 12:38
 */
@Data
public class WepayBillCsvInfo {
    /**
     * 交易创建时间
     */
    @CsvBindByPosition(position = 0)
    private String createDate;

    /**
     * 交易来源
     */
    @CsvBindByPosition(position = 1)
    private String source;

    /**
     * 交易对方
     */
    @CsvBindByPosition(position = 2)
    private String boss;

    /**
     * 商品名称
     */
    @CsvBindByPosition(position = 3)
    private String productName;

    /**
     * 金额
     */
    @CsvBindByPosition(position = 5)
    private String amount;

    /**
     * 收入/支出
     */
    @CsvBindByPosition(position = 4)
    private String incomeExpend;

    /**
     * 交易单号
     */
    @CsvBindByPosition(position = 8)
    private String orderId;

    private String openId;
}
