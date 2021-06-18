package com.jiqunar.light.service.moonlight;

import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.moonlight.*;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.moonlight.BillEditResponse;
import com.jiqunar.light.model.response.moonlight.BillListResponse;
import com.jiqunar.light.model.response.moonlight.BillStatisticsResponse;
import com.jiqunar.light.model.response.moonlight.YearBillResponse;

/**
 * 账单信息 服务类
 *
 * @author auto generator
 * @since 2020-07-28
 */
public interface BillService extends IService<BillEntity> {
    /**
     * 分页获取账单信息
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);

    /**
     * 按日期范围查看账单信息
     *
     * @param request
     * @return
     */
    BillListResponse getByDate(BillListRequest request);

    /**
     * 查看账单信息
     *
     * @param request
     * @return
     */
    BillEditResponse getById(BillEditGetRequest request);

    /**
     * 编辑账单信息
     *
     * @param request
     * @return
     */
    BaseResponse editBill(BillEditRequest request);

    /**
     * 账单统计信息
     *
     * @param request
     * @return
     */
    BillStatisticsResponse billStatistics(BillStatisticsRequest request);

    /**
     * 年度账单
     *
     * @param request
     * @return
     */
    YearBillResponse billYear(BillYearRequest request);


    /**
     * 更新售卖数量
     *
     * @param id
     * @return
     */
    int updateSaleCount(Long id);
}
