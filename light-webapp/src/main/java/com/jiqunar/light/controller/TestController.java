package com.jiqunar.light.controller;

import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.common.DesUtils;
import com.jiqunar.light.common.FunnelLimitUtil;
import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.model.mq.MQConfig;
import com.jiqunar.light.model.request.moonlight.BillYearRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.service.moonlight.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * 权限相关接口
 *
 * @author jieguang.wang
 * @date 2020/6/2 17:17
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试相关接口")
@Validated
public class TestController {
    @Autowired
    private RedistUtils redistUtils;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQConfig mqConfig;

    @Autowired
    private BillService billService;

    @Autowired
    private RedissonClient redissonClient;

    private static final FunnelLimitUtil funnelLimitUtil = new FunnelLimitUtil(600, 100);
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    private static Map<Long, BillEntity> giftMap = new ConcurrentHashMap<>();

    /**
     * 年度账单（分享）
     *
     * @param request
     * @return
     */
    @PostMapping("/billYearShare")
    @ApiOperation("年度账单（分享）")
    public BaseResponse billYearShare(@RequestBody BillYearRequest request) {
        request.setOpenId(DesUtils.decrypt(DesUtils.DEFAULT_PASSWORD, request.getEncryptOpenId()));
        return BaseResponse.success(billService.billYear(request));
    }

    /**
     * 测试随机数
     *
     * @return
     */
    @GetMapping("/testRandom")
    @ApiOperation("测试随机数")
    public BaseResponse testRandom() throws InterruptedException {
        // synchronized 直接使用类锁

        BillEntity billEntity = giftMap.get(14539L);
        synchronized (billEntity) {
            if (billEntity.getUpdaterId() >= billEntity.getCreaterId()) {
                return BaseResponse.fail("没有库存了");
            } else {
                Thread.sleep(50);
                int result = billService.updateSaleCount(billEntity.getId(), billEntity.getUpdaterId().intValue());
                if (result > 0) {
                    billEntity.setUpdaterId(billEntity.getUpdaterId() + 1);
                }
                return BaseResponse.success(result > 0 ? "抢购成功" : "抢购失败");
            }
        }
//        String incKey = "incKey_" + billEntity.getId();
//        Long saleCount = redistUtils.get(incKey, Long.class);
//        if (saleCount != null && saleCount >= billEntity.getCreaterId()) {
//            return BaseResponse.fail("没有库存了");
//        } else {
//            Thread.sleep(50);
//            redistUtils.inc(incKey);
//            return BaseResponse.success("抢购成功");
//        }

//        String incKey = "incKey_" + billEntity.getId();
//        Long saleCount = redistUtils.inc(incKey);
//        if (saleCount != null && saleCount > billEntity.getCreaterId()) {
//            return BaseResponse.fail("没有库存了");
//        } else {
//            Thread.sleep(50);
//            int result = billService.updateSaleCount(billEntity.getId());
//            return BaseResponse.success("抢购成功");
//        }

//        String redissonKey = "redissonKey_" + billEntity.getId();
//        RLock redissonClientLock = redissonClient.getLock(redissonKey);
//        try {
//            redissonClientLock.lock();
//            String incKey = "incKey_" + billEntity.getId();
//            Long saleCount = redistUtils.get(incKey, Long.class);
//            if (saleCount != null && saleCount >= billEntity.getCreaterId()) {
//                return BaseResponse.fail("没有库存了");
//            } else {
//                Thread.sleep(50);
//                redistUtils.inc(incKey);
//                int result = billService.updateSaleCount(billEntity.getId());
//                return BaseResponse.success("抢购成功");
//            }
//        } finally {
//            redissonClientLock.unlock();
//        }

//        logger.info(String.valueOf(funnelLimitUtil.addWater(1)));
//        // 耗时30ms的方法
//        Thread.sleep(300);
//        return BaseResponse.success("complete");

//        if (!funnelLimitUtil.addWater(1)) {
//            return BaseResponse.fail("被限流");
//        }
//        Integer[] source = {12, 5, 6, 78, 44, 99};
//        for (int i = 0; i < source.length; i++) {
//            for (int j = i; j < source.length; j++) {
//                if (source[i] > source[j]) {
//                    int temp = source[i];
//                    source[i] = source[j];
//                    source[j] = temp;
//                }
//            }
//        }
//        Double randomDouble = RandomUtils.nextDouble(0, 1);
//        return BaseResponse.success(source);
    }

    /**
     * 测试随机数
     *
     * @return
     */
    @GetMapping("/testRedis")
    @ApiOperation("测试随机数")
    public BaseResponse testRedis() {
        Boolean result = false;
        String cacheKey = "fuck";
        Map<Boolean, String> exceedLimitRecordMap = new HashMap<>();
        boolean exceedLimit = !CollectionUtils.isEmpty(exceedLimitRecordMap)
                && new ArrayList<Boolean>(exceedLimitRecordMap.keySet()).get(0);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date newDate = cal.getTime();

        if (redistUtils.exist(cacheKey)) {
            String availableCashPoolAmount = redistUtils.get(cacheKey, "1", "2", "3");
            result = BigDecimal.valueOf(10.47).compareTo(new BigDecimal(availableCashPoolAmount)) > 0;
        } else {
            redistUtils.put(cacheKey, BigDecimal.valueOf(30000).toPlainString());
        }

        // 减少奖金池
        BigDecimal nowCashPoolAmount = new BigDecimal(redistUtils.get(cacheKey))
                .subtract(BigDecimal.valueOf(10.47)).setScale(2, BigDecimal.ROUND_HALF_UP);
        redistUtils.put(cacheKey, nowCashPoolAmount.toPlainString());

        LocalDateTime newRemindTime = LocalDateTime.now().plusMinutes(-5);
        Long aa = Duration.between(LocalDateTime.now(), newRemindTime).toMinutes();
        newRemindTime = newRemindTime.plusMinutes(10);
        Integer aaa = newRemindTime.compareTo(LocalDateTime.now());
        return BaseResponse.success(result);
    }

    /**
     * mq发送消息
     *
     * @return
     */
    @GetMapping("/mqSend")
    @ApiOperation("mq发送消息")
    public BaseResponse mqSend(Integer count) {
        for (int i = 0; i < count; i++) {
            rabbitTemplate.convertAndSend(mqConfig.getDefaultExchange(), mqConfig.getDefaultRouteKey(), count);
        }
        return BaseResponse.success(count);
    }

    /**
     * webflux测试
     *
     * @return
     */
    @GetMapping("/webflux")
    @ApiOperation("webflux测试")
    public Mono<BaseResponse> webflux() {
        giftMap.put(14539L, billService.getById(14539L));
        return Mono.just(BaseResponse.success("webflux测试：Mono.just"));
    }

    /**
     * webflux1测试
     *
     * @return
     */
    @GetMapping("/webflux1")
    @ApiOperation("webflux1测试")
    public Flux<BaseResponse> webflux1() {
        return Flux.just(BaseResponse.success("webflux1测试"), BaseResponse.success("webflux1测试：Flux.just"));
    }

    /**
     * webflux2测试
     *
     * @return
     */
    @GetMapping("/webflux2")
    @ApiOperation("webflux2测试")
    public Flux<BaseResponse> webflux2() {
        return Flux.error(new IllegalStateException("webflux2测试：Flux.error"));
    }

    /**
     * webflux3测试
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/webflux3")
    @ApiOperation("webflux3测试")
    public Mono<BaseResponse> webflux3() {
        System.out.println(Thread.currentThread().getName() + DateUtils.getDateTime(LocalDateTime.now()));
        BaseResponse result = sleep();
        System.out.println(Thread.currentThread().getName() + DateUtils.getDateTime(LocalDateTime.now()));
        return Mono.just(result);
    }

    /**
     * webflux4测试
     *
     * @return
     */
    @SneakyThrows
    @GetMapping("/webflux4")
    @ApiOperation("webflux4测试")
    public Mono<BaseResponse> webflux4() {
        System.out.println(Thread.currentThread().getName() + DateUtils.getDateTime(LocalDateTime.now()));
        Mono<BaseResponse> result = Mono.fromSupplier(() -> sleep());
        System.out.println(Thread.currentThread().getName() + DateUtils.getDateTime(LocalDateTime.now()));
        return result;
    }

    private BaseResponse sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return BaseResponse.systemException(e.getMessage());
        }
        return BaseResponse.success(Thread.currentThread().getName());
    }
}
