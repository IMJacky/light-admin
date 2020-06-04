package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.RoleMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 角色菜单 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {
    /**
     * 分页获取角色菜单
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
