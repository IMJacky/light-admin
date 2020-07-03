package com.jiqunar.light.controller;

import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

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

    /**
     * 测试随机数
     *
     * @return
     */
    @GetMapping("/testRandom")
    @ApiOperation("测试随机数")
    public BaseResponse testRandom() {
        return BaseResponse.success(RandomUtils.nextDouble(0, 1));
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
        return BaseResponse.success(result);
    }
}
