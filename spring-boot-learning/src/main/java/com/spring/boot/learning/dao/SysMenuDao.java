package com.spring.boot.learning.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.learning.model.SysMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author yangyk
 * @since 2020-06-03
 */
public interface SysMenuDao extends BaseMapper<SysMenuModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:41
	 * description:
	 **/
	List<SysMenuModel> getUserMenuInfo(@Param("username") String username);
}
