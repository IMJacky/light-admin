package com.jiqunar.light.model.response.upms;

import lombok.Data;

import java.util.List;

/**
 * 菜单树形结构响应实体
 * @author jieguang.wang
 * @date 2020/7/15 17:56
 */
@Data
public class MenuTreeResponse {
    /**
     * 已经拥有的菜单key集合
     */
    private List<String> menuTreeCheckedList;

    /**
     * 所有的菜单集合
     */
    private List<MenuTree> menuTreeList;
}
