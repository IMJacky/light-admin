package com.jiqunar.light.model.entity.log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 日志
 *
 * @author auto generator
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ll_log")
@ApiModel(value = "LogEntity对象", description = "日志")
public class LogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联表的主键Id
     */
    @ApiModelProperty(value = "关联表的主键Id")
    @TableField("relate_id")
    private Long relateId;

    /**
     * 操作类型：0-新增，1-修改，2-删除
     */
    @ApiModelProperty(value = "操作类型：0-新增，1-修改，2-删除")
    @TableField("operate_type")
    private Integer operateType;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    @TableField("log_type")
    private Integer logType;

    /**
     * 日志子类型
     */
    @ApiModelProperty(value = "日志子类型")
    @TableField("log_sub_type")
    private Integer logSubType;

    /**
     * 日志信息
     */
    @ApiModelProperty(value = "日志信息")
    @TableField("message")
    private String message;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

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
     * 是否删除（0未删除，1已删除）
     */
    @ApiModelProperty(value = "是否删除（0未删除，1已删除）")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
