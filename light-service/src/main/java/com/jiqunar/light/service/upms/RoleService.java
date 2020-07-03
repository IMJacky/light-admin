package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.upms.RoleEditRequest;
import com.jiqunar.light.model.request.upms.RoleListRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 角色 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface RoleService extends IService<RoleEntity> {
    /**
     * 分页获取角色
     *
     * @param request
     * @return
     */
    PageResponse page(RoleListRequest request);

    /**
     * 编辑角色
     *
     * @param request
     * @return
     */
    Long edit(RoleEditRequest request);
}
