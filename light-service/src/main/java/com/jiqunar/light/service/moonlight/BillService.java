package com.jiqunar.light.service.moonlight;

import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.BillEditRequest;
import com.jiqunar.light.model.request.moonlight.BillListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.moonlight.BillEditResponse;
import com.jiqunar.light.model.response.moonlight.BillListResponse;

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
}
