package com.jiqunar.light.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应实体
 *
 * @author jieguang.wang
 * @date 2020/5/7 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    /**
     * 总数
     */
    private Long totalCount;

    /**
     * 列表数据
     */
    private List<T> data;
}
