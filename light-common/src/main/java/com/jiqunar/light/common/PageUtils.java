package com.jiqunar.light.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 分页类简单封装
 *
 * @author jieguang.wang
 * @date 2020/5/7 15:30
 */
public class PageUtils {
    /**
     * 获取分页数据
     * @param pageInfo pagehelper的返回结果
     * @return 自定义的分页信息
     */
    public static PageResponse getPageResponse(IPage<?> pageInfo) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalCount(pageInfo.getTotal());
        pageResponse.setData(pageInfo.getRecords());
        return pageResponse;
    }
}
