package com.jiqunar.light.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.common.RabbitMQUtils;
import com.jiqunar.light.dao.moonlight.BillMapper;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.moonlight.AlipayBillCsvInfo;
import com.jiqunar.light.model.response.moonlight.WepayBillCsvInfo;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MQ 服务测试
 *
 * @author jieguang.wang
 * @date 2020/7/24 14:37
 */
@Service
public class MQService {
    private Logger logger = LoggerFactory.getLogger(MQService.class);
    @Autowired
    private BillMapper billMapper;

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

    /**
     * 支付宝账单消息接收消费
     *
     * @param alipayBillCsvInfoJson
     * @param channel
     * @param tag
     */
    @SneakyThrows
    @RabbitListener(queues = "${custom.mq.alipayBillQueue}")
    public void mqReciveAlipay(String alipayBillCsvInfoJson, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        logger.info("消费内容为：{}", alipayBillCsvInfoJson);
        AlipayBillCsvInfo m = JSON.parseObject(alipayBillCsvInfoJson, AlipayBillCsvInfo.class);
        if (m != null) {
            BillEntity billEntity = billMapper.selectOne(new LambdaQueryWrapper<BillEntity>().eq(BillEntity::getOpenId, m.getOpenId())
                    .eq(BillEntity::getOrderId, m.getOrderId().trim()));
            if (billEntity == null) {
                billEntity = new BillEntity();
            } else {
                billEntity.setUpdateDate(LocalDateTime.now());
            }
            billEntity.setOpenId(m.getOpenId());
            billEntity.setAmount(new BigDecimal(m.getAmount().trim()));
            billEntity.setBillDate(DateUtils.getDateTime(m.getCreateDate().trim()));
            billEntity.setBillType(m.getIncomeExpend().trim().equals("收入") ? 1 : 0);
            billEntity.setBoss(m.getBoss().trim());
            billEntity.setProduct(m.getProductName().trim());
            billEntity.setTag("支付宝");
            billEntity.setSubTag(m.getSource().trim());
            billEntity.setOrderId(m.getOrderId().trim());
            if (billEntity.getId() != null && billEntity.getId() > 0) {
                billMapper.updateById(billEntity);
            } else {
                billMapper.insert(billEntity);
            }
        }
        RabbitMQUtils.askMessage(channel, tag, logger);
    }

    /**
     * 微信账单消息接收消费
     *
     * @param wepayBillCsvInfoJson
     * @param channel
     * @param tag
     */
    @SneakyThrows
    @RabbitListener(queues = "${custom.mq.wepayBillQueue}")
    public void mqReciveWepay(String wepayBillCsvInfoJson, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        logger.info("消费内容为：{}", wepayBillCsvInfoJson);
        WepayBillCsvInfo m = JSON.parseObject(wepayBillCsvInfoJson, WepayBillCsvInfo.class);
        if (m != null) {
            BillEntity billEntity = billMapper.selectOne(new LambdaQueryWrapper<BillEntity>().eq(BillEntity::getOpenId, m.getOpenId())
                    .eq(BillEntity::getOrderId, m.getOrderId().trim()));
            if (billEntity == null) {
                billEntity = new BillEntity();
            } else {
                billEntity.setUpdateDate(LocalDateTime.now());
            }
            billEntity.setOpenId(m.getOpenId());
            billEntity.setAmount(new BigDecimal(m.getAmount().replace("¥", "").trim()));
            billEntity.setBillDate(DateUtils.getDateTime(m.getCreateDate().trim()));
            billEntity.setBillType(m.getIncomeExpend().trim().equals("收入") ? 1 : 0);
            if (!m.getBoss().trim().equals("/")) {
                billEntity.setBoss(m.getBoss().trim());
            }
            if (!m.getProductName().trim().equals("/")) {
                billEntity.setProduct(m.getProductName().trim());
            }
            billEntity.setTag("微信");
            billEntity.setSubTag(m.getSource().trim());
            billEntity.setOrderId(m.getOrderId().trim());
            if (billEntity.getId() != null && billEntity.getId() > 0) {
                billMapper.updateById(billEntity);
            } else {
                billMapper.insert(billEntity);
            }
        }
        RabbitMQUtils.askMessage(channel, tag, logger);
    }
}
