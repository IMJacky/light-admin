package com.jiqunar.light.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体基类
 * @author jieguang.wang
 * @date 2020/5/28 16:53
 */
@Data
public class BaseEntity {
    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * 创建人Id
     */
    @ApiModelProperty(value = "创建人Id")
    @TableField("creater_id")
    private Long createrId;

    /**
     * 创建人名字
     */
    @ApiModelProperty(value = "创建人名字")
    @TableField("creater_name")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 更新人Id
     */
    @ApiModelProperty(value = "更新人Id")
    @TableField("updater_id")
    private Long updaterId;

    /**
     * 更新人名字
     */
    @ApiModelProperty(value = "更新人名字")
    @TableField("updater_name")
    private String updaterName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("update_date")
    private LocalDateTime updateDate;

    /**
     * 是否删除（0未删除，1已删除）
     */
    @ApiModelProperty(value = "是否删除（0未删除，1已删除）")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;
}
