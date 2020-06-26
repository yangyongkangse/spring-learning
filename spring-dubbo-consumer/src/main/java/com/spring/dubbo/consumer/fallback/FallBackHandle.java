package com.spring.dubbo.consumer.fallback;

import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * author: yangyk
 * date:2020/6/22 10:10
 * description:
 **/
public class FallBackHandle {
	public static ResponseEntity handleException(Exception exception) {
		return ResponseEntity.build(500, Constant.ERROR_MSG, exception.getStackTrace());
	}

	public static ResponseEntity handleResourceNotFoundException(ResourceNotFoundException exception) {
		return ResponseEntity.build(404, Constant.RESOURCE_NOT_FOUND_MSG, exception.getStackTrace());
	}

	public static ResponseEntity handleNotPermissionException(IllegalArgumentException exception) {
		return ResponseEntity.build(401, Constant.NOT_PERMISSION_MSG, exception.getStackTrace());
	}
}
