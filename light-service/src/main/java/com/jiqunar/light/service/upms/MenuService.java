package com.jiqunar.light.service.upms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.model.request.upms.MenuEditRequest;
import com.jiqunar.light.model.request.upms.MenuListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.upms.MenuListResponse;

import java.util.List;
import java.util.Map;

/**
 * 菜单 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 分页获取菜单
     *
     * @param request
     * @return
     */
    PageResponse page(MenuListRequest request);

    /**
     * 获取菜单列表
     *
     * @param request
     * @return
     */
    List<MenuListResponse> getMenuList(MenuListRequest request);

    /**
     * 编辑菜单
     *
     * @param request
     * @return
     */
    Long edit(MenuEditRequest request);

    /**
     * 查看所有菜单
     *
     * @return
     */
    Map<Long, String> mapAll();
}
