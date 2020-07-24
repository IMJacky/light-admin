package com.jiqunar.light.model.response.upms;

import lombok.Data;

import java.util.List;

/**
 * 用户菜单树形结构
 * @author jieguang.wang
 * @date 2020/7/15 16:43
 */
@Data
public class UserMenuTree {
    /**
     * 节点key
     */
    private String key;

    /**
     * 标题
     */
    private String title;

    /**
     * 子节点集合
     */
    private List<UserMenuTree> children;
}
