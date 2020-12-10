package com.jiqunar.light.dao.moonlight;

import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiqunar.light.model.request.moonlight.BillStatisticsRequest;
import com.jiqunar.light.model.request.moonlight.BillYearRequest;
import com.jiqunar.light.model.response.moonlight.StatisticsDetail;
import com.jiqunar.light.model.response.moonlight.YearBillResponse;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 账单信息 Mapper 接口
 *
 * @author auto generator
 * @since 2020-07-28
 */
@Repository
public interface BillMapper extends BaseMapper<BillEntity> {
    /**
     * 账单统计信息
     *
     * @param request
     * @return
     */
    List<StatisticsDetail> billStatistics(BillStatisticsRequest request);

    /**
     * 年度账单
     *
     * @param request
     * @return
     */
    Collection<Collection<YearBillResponse>> billYear(BillYearRequest request);
}
