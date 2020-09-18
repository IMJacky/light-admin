package com.jiqunar.light.model.response.upms;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 工作台响应实体
 *
 * @author jieguang.wang
 * @date 2020/9/7 14:34
 */
@Data
@ApiModel(value = "工作台响应实体", description = "工作台响应实体")
public class WorkplaceResponse {
    /**
     * 用户总数
     */
    @ApiModelProperty(value = "用户总数")
    private Integer userCount;

    /**
     * 部门总数
     */
    @ApiModelProperty(value = "部门总数")
    private Integer deptCount;

    /**
     * 岗位总数
     */
    @ApiModelProperty(value = "岗位总数")
    private Integer jobCount;

    /**
     * 功能列表
     */
    @ApiModelProperty(value = "功能列表")
    private List<Function> functionList;

    /**
     * 快捷导航列表
     */
    @ApiModelProperty(value = "快捷导航列表")
    private List<QuickNav> quickNavList;

    @Data
    @ApiModel(value = "系统功能实体", description = "系统功能实体")
    public static class Function {
        /**
         * 开发人
         */
        @ApiModelProperty(value = "开发人")
        private String developer;

        /**
         * 头像地址
         */
        @ApiModelProperty(value = "头像地址")
        private String avatarUrl;

        /**
         * 所属部门
         */
        @ApiModelProperty(value = "所属部门")
        private String deptName;

        /**
         * 功能标题
         */
        @ApiModelProperty(value = "功能标题")
        private String title;

        /**
         * 功能描述
         */
        @ApiModelProperty(value = "功能描述")
        private String description;

        /**
         * 完成日期
         */
        @ApiModelProperty(value = "完成日期")
        private LocalDate completeDate;
    }

    @Data
    @ApiModel(value = "快捷导航实体", description = "快捷导航实体")
    public static class QuickNav {
        /**
         * 标题
         */
        @ApiModelProperty(value = "标题")
        private String title;

        /**
         * 地址
         */
        @ApiModelProperty(value = "地址")
        private String url;
    }
}
