package com.sprong.nacos.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.spring.api.model.ResponseEntity;
import com.spring.api.tools.Constant;
import com.sprong.nacos.sentinel.fallback.FallBackHandle;
import com.sprong.nacos.sentinel.handle.BlockExceptionHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: yangyk
 * date:2020/6/22 8:42
 * description:test SentinelResource
 **/
@RestController
@RequestMapping("/api/sentinel")
public class SentinelResourceController {

	/**
	 * author: yangyk
	 * date:2020/6/22 8:48
	 * description:SentinelResource内定义了 value,EntryType,resourceType,blockHandler,blockHandlerClass,fallback,defaultFallback,fallbackClass
	 * value:资源名,控制台通过资源名找到对应规则
	 * EntryType 类型，可选项（默认为 EntryType.OUT）
	 * blockHandler/blockHandlerClass:blockHandler对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，
	 * 返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。。blockHandler 函数默认需要和原方法在同一个类中。
	 * 若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * fallback / fallbackClass：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。
	 * fallback 函数签名和位置要求：返回值类型必须与原函数返回值类型一致；方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
	 * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。
	 * 若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：返回值类型必须与原函数返回值类型一致,方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
	 * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出
	 * blockHandlerClass用于处理配置规则异常,fallbackClass用于处理业务异常,如果两个同时配置blockHandlerClass优先级高于fallbackClass
	 **/
	@GetMapping(value = "/hotKey")
	@SentinelResource(value = "hotKey", blockHandlerClass = BlockExceptionHandle.class, blockHandler = "handleException", fallbackClass = FallBackHandle.class, fallback = "handleNotPermissionException")
	public ResponseEntity testHotKey(@RequestParam("id") Long id, @RequestParam("name") String name) {
		return ResponseEntity.build(200, Constant.SUCCESS_MSG, id + name);
	}

}
