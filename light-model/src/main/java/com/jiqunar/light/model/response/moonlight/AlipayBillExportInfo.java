package com.jiqunar.light.model.response.moonlight;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 支付宝账单excel实体
 *
 * @author jieguang.wang
 * @date 2020/10/13 19:15
 */
@Data
public class AlipayBillExportInfo {
    /**
     * 交易创建时间
     */
    @ExcelProperty(index = 2)
    private String createDate;

    /**
     * 交易对方
     */
    @ExcelProperty(index = 7)
    private String boss;

    /**
     * 商品名称
     */
    @ExcelProperty(index = 8)
    private String productName;

    /**
     * 金额
     */
    @ExcelProperty(index = 9)
    private String amount;

    /**
     * 收入/支出
     */
    @ExcelProperty(index = 10)
    private String incomeExpend;

    /**
     * 资金状态
     */
    @ExcelProperty(index = 15)
    private String fundsStatus;
}
