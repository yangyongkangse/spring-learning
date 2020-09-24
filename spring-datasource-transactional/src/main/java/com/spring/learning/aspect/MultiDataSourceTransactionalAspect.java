package com.spring.learning.aspect;

import com.spring.learning.annonation.MultiDataSourceTransactional;
import com.spring.learning.util.SpringContextUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:35
 * Description: 多数据源事务控制器切面
 */
@Aspect
@Component
@Log4j2
public class MultiDataSourceTransactionalAspect {

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 15:57
	 * Description: 定义切面，只置入带 @MultiDataSourceTransactional 注解的方法或类
	 */
	@Pointcut("@annotation(com.spring.learning.annonation.MultiDataSourceTransactional)")
	public void multiDataSourceTransactionalAspectAspect() {

	}

	/**
	 * Author: yangyk
	 * Date: 2020/9/24 15:57
	 * Description: 控制事务提交及回滚
	 */
	@Around(value = "multiDataSourceTransactionalAspectAspect() && @annotation(multiDataSourceTransactional)")
	public Object transactionalGroupAspectAround(ProceedingJoinPoint pjp, MultiDataSourceTransactional multiDataSourceTransactional) throws Throwable {
		Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<>();
		Stack<TransactionStatus> transactionStatusStack = new Stack<>();
		if (multiDataSourceTransactional.value().length < 1) {
			log.warn("[开启事务失败]：无指定多数据源管理器");
			return null;
		}
		for (String item : multiDataSourceTransactional.value()) {
			DataSourceTransactionManager dbManager = (DataSourceTransactionManager) SpringContextUtil.getBean(item);
			TransactionStatus transactionStatus = dbManager.getTransaction(new DefaultTransactionDefinition());
			dataSourceTransactionManagerStack.push(dbManager);
			transactionStatusStack.push(transactionStatus);
		}
		try {
			Object obj = pjp.proceed();
			while (!dataSourceTransactionManagerStack.isEmpty()) {
				dataSourceTransactionManagerStack.pop().commit(transactionStatusStack.pop());
			}
			return obj;
		} catch (Exception e) {
			log.warn(e.getMessage());
			while (!dataSourceTransactionManagerStack.isEmpty()) {
				dataSourceTransactionManagerStack.pop().rollback(transactionStatusStack.pop());
			}
			return null;
		}
	}

}
