package com.jiqunar.light.dao.log;

import com.jiqunar.light.model.entity.log.LogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiqunar.light.model.request.log.LogListRequest;
import com.jiqunar.light.model.response.common.BarResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志 Mapper 接口
 *
 * @author auto generator
 * @since 2020-09-07
 */
@Repository
public interface LogMapper extends BaseMapper<LogEntity> {
    /**
     * 日志统计柱状图
     *
     * @param request
     * @return
     */
    List<BarResponse> logBar(LogListRequest request);
}
