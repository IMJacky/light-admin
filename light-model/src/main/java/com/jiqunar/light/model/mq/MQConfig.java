package com.jiqunar.light.model.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MQ的自定义配置
 * @author jieguang.wang
 * @date 2020/7/24 14:13
 */
@Component
@ConfigurationProperties(prefix = "custom.mq")
@Data
public class MQConfig {
    /**
     * 默认交换器
     */
    private String  defaultExchange;

    /**
     * 默认队列名
     */
    private String  defaultQueue;

    /**
     * 默认路由key
     */
    private String  defaultRouteKey;

    /**
     * 支付宝账单交换机
     */
    private String  alipayBillExchange;

    /**
     * 支付宝账单队列名
     */
    private String  alipayBillQueue;

    /**
     * 支付宝账单路由key
     */
    private String  alipayBillRouteKey;

    /**
     * 微信账单交换机
     */
    private String  wepayBillExchange;

    /**
     * 微信账单队列名
     */
    private String  wepayBillQueue;

    /**
     * 微信账单路由key
     */
    private String  wepayBillRouteKey;
}
