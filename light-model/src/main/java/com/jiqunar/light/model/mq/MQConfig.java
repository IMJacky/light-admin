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
}
