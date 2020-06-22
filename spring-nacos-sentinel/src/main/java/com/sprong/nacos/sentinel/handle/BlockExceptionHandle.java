package com.sprong.nacos.sentinel.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;

/**
 * @Author: yangyk Created with IntelliJ IDEA
 * @date: 2020/6/22 9:33
 * @description: 处理Sentinel全局异常
 */
public class BlockExceptionHandle {

	public static ResponseEntity handleException(BlockException exception) {
		return ResponseEntity.build(500, Constant.ERROR_MSG, exception.getStackTrace());
	}

	public static ResponseEntity handleResourceNotFoundException(BlockException exception) {
		return ResponseEntity.build(404, Constant.RESOURCE_NOT_FOUND_MSG, exception.getStackTrace());
	}

	public static ResponseEntity handleNotPermissionException(BlockException exception) {
		return ResponseEntity.build(401, Constant.NOT_PERMISSION_MSG, exception.getStackTrace());
	}
}
