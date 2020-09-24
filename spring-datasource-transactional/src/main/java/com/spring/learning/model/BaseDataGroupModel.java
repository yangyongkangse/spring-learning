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

/**
 * Author: yangyk
 * Date: 2020/9/24 15:52
 * Description:  物料基础数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_data_group")
@ApiModel(value = "BaseDataGroupModel对象", description = "物料基础数据 ")
public class BaseDataGroupModel extends Model<BaseDataGroupModel> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "21位组合码")
	private String groupCode;

	@ApiModelProperty(value = "21位组合码描述")
	private String groupDesc;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
