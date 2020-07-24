package com.jiqunar.light.serviceimpl.mq;

import com.jiqunar.light.common.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * MQ 服务测试
 *
 * @author jieguang.wang
 * @date 2020/7/24 14:37
 */
@Service
public class MQTestService {
    private Logger logger = LoggerFactory.getLogger(MQTestService.class);

    /**
     * mq消息接收消费
     *
     * @param payload
     * @param channel
     * @param tag
     */
    @SneakyThrows
    @RabbitListener(queues = "${custom.mq.defaultQueue}")
    public void mqRecive(String payload, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        Thread.sleep(1000 * 10);
        logger.info("消费内容为：{}", payload);
        RabbitMQUtils.askMessage(channel, tag, logger);
    }
}
