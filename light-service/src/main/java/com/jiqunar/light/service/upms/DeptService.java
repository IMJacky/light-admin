package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.upms.DeptEditRequest;
import com.jiqunar.light.model.request.upms.DeptListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.common.CascadeResponse;

import java.util.List;
import java.util.Map;

/**
 * 部门 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface DeptService extends IService<DeptEntity> {
    /**
     * 分页获取部门
     *
     * @param request
     * @return
     */
    PageResponse page(DeptListRequest request);

    /**
     * 编辑部门
     *
     * @param request
     * @return
     */
    Long edit(DeptEditRequest request);

    /**
     * 查看所有部门
     *
     * @return
     */
    Map<Long, String> mapAll();

    /**
     * 查看所有部门(级联方式)
     *
     * @return
     */
    List<CascadeResponse> cascadeAll();
}
