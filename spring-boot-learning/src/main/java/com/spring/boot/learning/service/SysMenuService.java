package com.spring.boot.learning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.learning.model.SysMenuModel;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author yangyk
 * @since 2020-06-03
 */
public interface SysMenuService extends IService<SysMenuModel> {

	/**
	 * author: yangyk
	 * date:2020/6/3 16:41
	 * description:
	 **/
	List<SysMenuModel> getUserMenuInfo(String username);
}
