package com.spring.learning.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: yangyk
 * Date: 2020-07-24
 * Description: 条码详情表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_barcode_info")
@ApiModel(value = "BaseBarcodeInfoModel对象", description = "条码详情表")
public class BaseBarcodeInfoModel extends Model<BaseBarcodeInfoModel> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键识别码:GUID识别码，用于唯一区分")
	private Long id;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "操作人:操作人员的登陆账号")
	private String updateUserCode;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "备用1")
	private String spare1;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
