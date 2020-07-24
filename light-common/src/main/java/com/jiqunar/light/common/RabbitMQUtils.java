package com.jiqunar.light.common;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * MQ 工具类
 *
 * @author jieguang.wang
 * @date 2020/7/24 14:47
 */
public class RabbitMQUtils {
    public static void askMessage(Channel channel, long tag, final Logger logger) {
        askMessage(channel, tag, logger, false);
    }

    public static void askMessage(Channel channel, long tag, final Logger logger, boolean multiple) {
        try {
            channel.basicAck(tag, multiple);
        } catch (IOException e) {
            logger.error("RabbitMQ，IO异常，异常原因为：{}", e.getMessage());
        }
    }

    public static void rejectMessage(Channel channel, long tag, final Logger logger) {
        rejectMessage(channel, tag, logger, false, false);
    }

    public static void rejectAndBackMQ(Channel channel, long tag, final Logger logger) {
        rejectMessage(channel, tag, logger, false, true);
    }

    public static void rejectMessage(Channel channel, long tag, final Logger logger, boolean multiple, boolean request) {
        try {
            channel.basicNack(tag, multiple, request);
        } catch (IOException e) {
            logger.error("RabbitMQ，IO异常，异常原因为：{}", e.getMessage());
        }
    }
}
