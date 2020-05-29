package com.jiqunar.light.model.entity.upms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 岗位
 *
 * @author auto generator
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lu_job")
@ApiModel(value = "JobEntity对象", description = "岗位")
public class JobEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    @TableField("job_name")
    private String jobName;


}
