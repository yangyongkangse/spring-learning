package com.spring.learning.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.learning.annonation.SystemLog;
import com.spring.learning.model.SysLogModel;
import com.spring.learning.service.SysLogService;
import com.spring.learning.util.Constant;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: yangyk
 * Date: 2020/9/24 15:57
 * Description: SystemLogAspect
 */
@Aspect
@Component
@Log4j2
public class SystemLogAspect {

	private com.spring.learning.service.SysLogService sysLogService;

	@Autowired
	private void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	private HttpServletRequest request;

	@Autowired(required = false)
	private void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	/**
	 * Author: yangyk
	 * Date: 2020/7/24 15:39
	 * Description: 定义切面，只置入带 @SystemLog 注解的方法或类
	 * * Controller层切点，注解方式
	 */
	@Pointcut("@annotation(com.spring.learning.annonation.SystemLog)")
	public void controllerAspect() {

	}


	/**
	 * Author: yangyk
	 * Date: 2020/9/24 16:04
	 * Description: 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
	 */
	@AfterReturning("controllerAspect()")
	public void afterSuccess(JoinPoint joinPoint) {
		try {
			SysLogModel sysLogModel = dealArgs(joinPoint, Constant.SUCCESS_CODE_STR);
			sysLogService.saveSysLog(sysLogModel);
		} catch (Exception e) {
			log.error("AOP后置通知异常", e);
		}
	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 16:04
	 * Description: 后置通知(在方法执行之后并返回数据) 用于拦截Controller层调用失败的操作
	 */
	@AfterThrowing("controllerAspect()")
	public void afterError(JoinPoint joinPoint) {
		try {
			SysLogModel sysLogModel = dealArgs(joinPoint, Constant.ERROR_CODE_STR);
			sysLogService.saveSysLog(sysLogModel);
		} catch (Exception e) {
			log.error("AOP后置通知异常", e);
		}
	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 16:03
	 * Description: 处理日志参数
	 */
	public SysLogModel dealArgs(JoinPoint joinPoint, String flag) {
		try {
			Map<String, Object> vars = getControllerMethodInfo(joinPoint);
			String description = vars.get("description").toString();
			String type = vars.get("type").toString();
			//-- 请求的方法类型(post/get)
			String method = request.getMethod();
			String paramJson = getRequestParam(method, joinPoint);
			//请求url
			String url = request.getRequestURI();
			SysLogModel sysLogModel = new SysLogModel();
			//判断调用类型,如果是外围系统调用无法获取当前登录用户
			String bearerToken = request.getHeader("Authorization");
			if (StringUtils.isEmpty(bearerToken)) {
				String appKey = request.getHeader("appKey");
				sysLogModel.setCreateUser(appKey == null ? "admin" : appKey);
				sysLogModel.setUpdateUser(appKey == null ? "admin" : appKey);
			} else {
				sysLogModel.setCreateUser("admin");
				sysLogModel.setUpdateUser("admin");
			}
			sysLogModel.setDescription(description);
			sysLogModel.setType(type);
			sysLogModel.setRequestUrl(url);
			sysLogModel.setRequestParam(paramJson);
			sysLogModel.setResponseCode(flag);
			sysLogModel.setRemark(method);
			return sysLogModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 16:01
	 * Description:  获取注解中对方法的描述信息 用于Controller层注解
	 */
	public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) {
		Map<String, Object> vars = new HashMap<>(2);
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SystemLog controllerLog = method
				.getAnnotation(SystemLog.class);
		vars.put("description", controllerLog.description());
		vars.put("type", controllerLog.type());
		return vars;
	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 16:01
	 * Description: 根据不同请求获取不同参数
	 */
	public String getRequestParam(String method, JoinPoint joinPoint) {
		ObjectMapper objectMapper = new ObjectMapper();
		//根据不同请求获取不同参数
		String paramJson = Strings.EMPTY;
		try {
			if (Constant.GET_METHOD.equals(method)) {
				//请求参数
				Map<String, String[]> params = request.getParameterMap();
				paramJson = objectMapper.writeValueAsString(params);
			} else {
				Object[] arguments = joinPoint.getArgs();
				paramJson = objectMapper.writeValueAsString(arguments);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return paramJson;
	}


}
