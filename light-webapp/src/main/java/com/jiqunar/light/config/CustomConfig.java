package com.jiqunar.light.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * 自定义配置读取
 *
 * @author jieguang.wang
 * @date 2020/6/4 9:58
 */
@Data
@Configuration
public class CustomConfig {
    /**
     * 是否开启权限token验证
     */
    @Value("${custom.auth.open}")
    private Boolean authOpen;

    /**
     * 不开启时的默认用户id
     */
    @Value("${custom.auth.userid}")
    private Long authUserId;

    /**
     * 不开启时的默认用户名
     */
    @Value("${custom.auth.username}")
    private String authUserName;
}
