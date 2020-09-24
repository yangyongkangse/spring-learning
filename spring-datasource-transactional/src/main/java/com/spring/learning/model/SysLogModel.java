package com.spring.learning.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yangyk
 * @date: 2020-07-09
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_sys_log")
@ApiModel(value="SysLogModel对象", description="系统日志")
public class SysLogModel extends Model<SysLogModel> {

   private static final long serialVersionUID=1L;

   @ApiModelProperty(value = "主键")
   @TableId(value = "id", type = IdType.AUTO)
   private Long id;

   @ApiModelProperty(value = "日志描述")
   private String description;

   @ApiModelProperty(value = "类型")
   private String type;

   @ApiModelProperty(value = "请求路径")
   private String requestUrl;

   @ApiModelProperty(value = "请求参数")
   private String requestParam;

   @ApiModelProperty(value = "响应结果")
   private String responseCode;

   @ApiModelProperty(value = "创建时间")
   private Date createTime;

   @ApiModelProperty(value = "创建人")
   private String createUser;

   @ApiModelProperty(value = "修改时间")
   private Date updateTime;

   @ApiModelProperty(value = "修改人")
   private String updateUser;

   @ApiModelProperty(value = "是否删除:1是0否")
   private Integer delFlag;

   @ApiModelProperty(value = "备注")
   private String remark;


   @Override
   protected Serializable pkVal() {
	   return this.id;
   }

}
