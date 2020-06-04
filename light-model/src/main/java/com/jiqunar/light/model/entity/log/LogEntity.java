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
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    @TableField("log_type")
    private String logType;

    /**
     * 日志子类型
     */
    @ApiModelProperty(value = "日志子类型")
    @TableField("log_sub_type")
    private String logSubType;

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
     * 是否删除（0未删除，1已删除）
     */
    @ApiModelProperty(value = "是否删除（0未删除，1已删除）")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
