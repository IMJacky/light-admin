package com.jiqunar.light.model.request.skyzen;

import lombok.Data;

/**
 * @author jieguang.wang
 * @date 2020/5/7 19:03
 */
@Data
public class AddOrderRequest {
    /**
     * 工号
     */
    private String userJobNumber;

    /**
     * 订单流水号
     */
    private String orderSerailId;
}
